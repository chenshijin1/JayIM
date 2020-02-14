package jayim.ws;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import jayim.model.FriendMessage;
import jayim.model.Group;
import jayim.model.GroupUser;
import jayim.model.User;
import jayim.service.FriendMessageService;
import jayim.service.GroupService;
import jayim.service.GroupUserService;
import jayim.vo.SNSMData;
import jayim.vo.SNSMember;
import jayim.vo.SNSUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import jayim.utils.RedisUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.server.standard.SpringConfigurator;

@Controller
@ServerEndpoint(value = "/LLWS/{userId}",configurator=SpringConfigurator.class)
public class LLWS
{

    private static Logger logger = Logger.getLogger(LLWS.class);

//    @Autowired
//    QunWS qunWS;

    @Autowired
    FriendMessageService friendMessageService;

    @Autowired
    private GroupUserService groupUserService;

    @Autowired
    private GroupService groupService;

    //ConcurrentHashMap是线程安全的，而HashMap是线程不安全的。
    private static ConcurrentHashMap<String, Session> mapUS = new ConcurrentHashMap<String, Session>();

    private static ConcurrentHashMap<Session, String> mapSU = new ConcurrentHashMap<Session, String>();

    public static void sendMessage(String userId, String message) throws IOException {
        Session session = mapUS.get(userId);
        if(session != null){
            session.getBasicRemote().sendText(message);
        }
    }

    //连接建立成功调用的方法 
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId)
    {
        String jsonString = "{'content':'online','id':" + userId + ",'type':'onlineStatus'}";
        for (Session s : session.getOpenSessions())
        {       //循环发给所有在线的人
            s.getAsyncRemote().sendText(jsonString);   //上线通知
        }
        mapUS.put(userId + "", session);
        mapSU.put(session, userId + "");
        //更新redis中的用户在线状态
        RedisUtils.set(userId + "_status", "online");
        logger.info("用户" + userId + "进入llws,当前在线人数为" + mapUS.size());

    }

    //连接关闭调用的方法 
    @OnClose
    public void onClose(Session session)
    {
        String userId = mapSU.get(session);
        if (userId != null && !userId.equals(""))
        {
            //更新redis中的用户在线状态
            RedisUtils.set(userId + "_status", "offline");
            String jsonString = "{'content':'offline','id':" + userId + ",'type':'onlineStatus'}";
            for (Session s : session.getOpenSessions())
            {       //循环发给所有在线的人
                s.getAsyncRemote().sendText(jsonString);   //下线通知
            }
            mapUS.remove(userId);
            mapSU.remove(session);
            logger.info("用户" + userId + "退出llws,当前在线人数为" + mapUS.size());
        }
    }

    // 收到客户端消息后调用的方法 
    @OnMessage
    public void onMessage(String message, Session session)
    {
        JSONObject jsonObject = JSONObject.fromObject(message);
        String type = jsonObject.getJSONObject("to").getString("type");
        if (type.equals("onlineStatus"))
        {
            for (Session s : session.getOpenSessions())
            {        //循环发给所有在线的人
                JSONObject toMessage = new JSONObject();
                toMessage.put("id", jsonObject.getJSONObject("mine").getString("id"));
                toMessage.put("content", jsonObject.getJSONObject("mine").getString("content"));
                toMessage.put("type", type);
                s.getAsyncRemote().sendText(toMessage.toString());
            }
        }
        else
        {
            int toId = jsonObject.getJSONObject("to").getInt("id");
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = df.format(date);
            jsonObject.put("time", time);
            JSONObject toMessage = new JSONObject();
            toMessage.put("avatar", jsonObject.getJSONObject("mine").getString("avatar"));
            toMessage.put("type", type);
            toMessage.put("content", jsonObject.getJSONObject("mine").getString("content"));
            toMessage.put("timestamp", date.getTime());
            toMessage.put("time", time);
            toMessage.put("mine", false);
            toMessage.put("username", jsonObject.getJSONObject("mine").getString("username"));
            if (type.equals("friend") || type.equals("fankui"))
            {
                toMessage.put("id", jsonObject.getJSONObject("mine").getInt("id"));
            }
            else
            {
                toMessage.put("id", jsonObject.getJSONObject("to").getInt("id"));
            }
            System.out.println("当前会话类型"+type);
            switch (type)
            {
                case "friend":                                     //单聊,记录到mongo
                    if (mapUS.containsKey(toId + ""))
                    {               //如果在线，及时推送
                        mapUS.get(toId + "").getAsyncRemote().sendText(toMessage.toString());               //发送消息给对方
                        logger.info("单聊-来自客户端的消息:" + toMessage.toString());
                    }
                    else
                    {

                        System.out.println(toId);
                        //如果不在线 就记录到数据库，下次对方上线时推送给对方。
                        RedisUtils.lpush(toId + "_msg", toMessage.toString());
                        logger.info("单聊-对方不在线，消息已存入redis:" + toMessage.toString());
                    }
//                    //管他在不在线先保存一波,但是只要保存一份就行了;
                    FriendMessage friendMessage=new FriendMessage();
                    friendMessage.setFromUserId(jsonObject.getJSONObject("mine").getInt("id"));
                    friendMessage.setToUserId(toId);
                    friendMessage.setContent(toMessage.get("content").toString());
                    logger.info(friendMessage.getContent()+"----"+friendMessage.getFromUserId()+"---"+friendMessage.getToUserId());
                    if (friendMessageService!=null) friendMessageService.save(friendMessage);
                    else{
                        logger.info("friendMessageService为空");
                    }
                    break;
                case "group":
                    JSONArray memberList = JSONArray.fromObject(getSimpleMemberByGroupId(toId));  //获取群成员userId列表
                    if (memberList.size() > 0)
                    {
                        for (int i = 0; i < memberList.size(); i++)
                        {                            //发送到在线用户(除了发送者)
                            if (mapUS.containsKey(memberList.get(i)) &&
                                !memberList.get(i).equals(jsonObject.getJSONObject("mine").getInt("id") + ""))
                            {
                                session = mapUS.get(memberList.get(i));
                                session.getAsyncRemote().sendText(toMessage.toString());
                                logger.info("群聊-来自客户端的消息:" + toMessage.toString());
                            }
                            else if (memberList.get(i).equals(jsonObject.getJSONObject("mine").getInt("id") + ""))
                            {
                                //如果是发送者自己，不做任何操作。
                            }
                            else
                            {
                                //如果是离线用户,数据存到redis待用户上线后推送。
                                RedisUtils.lpush(memberList.get(i) + "_msg", toMessage.toString());
                                logger.info("群聊-对方不在线，消息已存入redis:" + toMessage.toString());

                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error)
    {
        String userId = mapSU.get(session);
        if (userId != null && !userId.equals(""))
        {
            //更新redis中的用户在线状态
            RedisUtils.set(userId + "_status", "offline");
            String jsonString = "{'content':'offline','id':" + userId + ",'type':'onlineStatus'}";
            for (Session s : session.getOpenSessions())
            {        //循环发给所有在线的人
                s.getAsyncRemote().sendText(jsonString);   //下线通知
            }
            mapUS.remove(userId);
            mapSU.remove(session);
            logger.info("用户" + userId + "退出llws！当前在线人数为" + mapUS.size());
        }
        logger.error("llws发生错误!");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     */
    public void sendMessage(Session session, String message)
    {
        session.getAsyncRemote().sendText(message);
    }


    //获取群成员列表(只含用户名)
    @RequestMapping(value = "/api/qun/getSimpleMemberByGroupId", produces = "text/plain; charset=utf-8")
    public @ResponseBody
    String getSimpleMemberByGroupId(int groupId)
    {
        return JSON.toJSONString(groupUserService.getSimpleMemberByGroupId(groupId));
    }

    //获取我的群组列表(加入的)
    @RequestMapping(value = "/api/qun/getGroupByUserId", produces = "text/plain; charset=utf-8")
    public @ResponseBody
    String getGroupByUserId(int userId)
    {
        List<Group> list = groupUserService.getByUserId(userId);
        return JSON.toJSONString(list);
    }

    //获取群成员信息 for sns
    @RequestMapping(value = "/api/qun/getByGroupId", produces = "text/plain; charset=utf-8")
    public @ResponseBody
    String getByGroupId(int id)
    {
        List<GroupUser> list = groupUserService.getByGroupId(id);
        User groupMaster = groupService.getGroupMaster(id);
        SNSMember member = new SNSMember();
        List<SNSUser> userList = new ArrayList<SNSUser>();
        for (int i = 0; i < list.size(); i++)
        {
            SNSUser snsUser = new SNSUser();
            snsUser.setId(list.get(i).getId());
            snsUser.setAvatar(list.get(i).getUser().getAvator());
            snsUser.setSign("无个性不签名");
            snsUser.setUsername(list.get(i).getUser().getUserName());
            userList.add(snsUser);
        }
        SNSUser snsMaster = new SNSUser();
        snsMaster.setAvatar(groupMaster.getAvator());
        snsMaster.setId(groupMaster.getId());
        snsMaster.setSign(groupMaster.getSign());
        snsMaster.setUsername(groupMaster.getNickName());
        SNSMData data = new SNSMData();
        data.setList(userList);
        data.setOwner(snsMaster);
        member.setCode(0);
        member.setData(data);
        return JSON.toJSONString(member);
    }
}
