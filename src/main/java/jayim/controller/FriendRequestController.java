package jayim.controller;

import com.alibaba.fastjson.JSON;
import jayim.model.Friend;
import jayim.model.FriendRequest;
import jayim.model.User;
import jayim.service.FriendRequestService;
import jayim.service.FriendService;
import jayim.service.FriendTypeService;
import jayim.service.UserService;
import jayim.vo.SNSRequest;
import jayim.vo.SNSRequestData;
import jayim.vo.SNSRequestUser;
import jayim.vo.SNSResponse;
import jayim.ws.LLWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/15 12:12
 */

@Controller
@RequestMapping("request")
public class FriendRequestController {
    @Autowired
    private FriendRequestService service;
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendTypeService friendTypeService;
    @Autowired
    private FriendRequestService friendRequestService;

    @RequestMapping(value = "getByPage", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getFriendRequestByPage(HttpSession session, int page){
        User me  = (User) session.getAttribute("user");
        SNSRequest request = new SNSRequest();
        request.setCode(0);
        request.setPages(page);
        List<SNSRequestData> list = new ArrayList<>();
        for(FriendRequest friendRequest : service.findFriendRequestByPage(me.getId(), page)){
            System.out.println(friendRequest);
            SNSRequestData data = new SNSRequestData();
            data.setId(friendRequest.getId());
            data.setContent(friendRequest.getContent());
            data.setUid(friendRequest.getUid());
            if(friendRequest.getIsRead() == 1) {
                data.setFrom(friendRequest.getFromId());
                data.setFrom_group(friendRequest.getFromGroup());
            }else{
                data.setFrom(0);
                data.setFrom_group(0);
            }
            data.setType(friendRequest.getType());
            data.setRemark(friendRequest.getRemark());
            data.setHref(friendRequest.getHref());
            data.setRead(friendRequest.getIsRead());
            data.setTime(friendRequest.getTime().toString());
            SNSRequestUser user = new SNSRequestUser();
            User tmp;
            if(friendRequest.getIsRead() == 1) {
                tmp = userService.getUserById(friendRequest.getFromId());
            }else{
                tmp = null;
            }
            if (tmp != null) {
                user.setId(tmp.getId());
                user.setAvatar(tmp.getAvator());
                user.setUsername(tmp.getNickName());
                user.setSign(tmp.getSign());
            }
            data.setUser(user);
            list.add(data);
        }
        for(FriendRequest friendRequest : service.findFriendRequestFromByPage(me.getId(), page)){
            User tmp = userService.getUserById(friendRequest.getUid());
            SNSRequestData data = new SNSRequestData();
            data.setId(friendRequest.getId());
            if(friendRequest.getIsRead() == 1) {
                data.setContent("您已向" + tmp.getNickName() + "发出好友申请");
            }else if (friendRequest.getIsRead() == 2){
                data.setContent(tmp.getNickName() + "同意了您的好友申请");
            }else{
                data.setContent(tmp.getNickName() + "拒绝了您的好友申请");
            }
            data.setUid(me.getId());
            data.setFrom(0);
            data.setFrom_group(0);
            data.setType(friendRequest.getType());
            data.setRemark(null);
            data.setHref(null);
            data.setRead(friendRequest.getIsRead());
            data.setTime(friendRequest.getTime().toString());
            SNSRequestUser user = new SNSRequestUser();
            data.setUser(user);
            list.add(data);
        }
        request.setData(list);
        return JSON.toJSONString(request, true);
    }

    @RequestMapping(value = "agreeFriend", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String agreeFriend(HttpSession session, int uid, int from_group, String group) throws IOException {
        User me = (User) session.getAttribute("user");
        //把好友添加到自己的列表
        Friend friend = new Friend();
        friend.setUserId(me.getId());
        friend.setFriendId(uid);
        friend.setBuildTime(new Date());
        int mygroup = friendTypeService.getByUserIdTypeName(me.getId(), group).getId();
        friend.setTypeId(mygroup);
        friendService.save(friend);
        //把自己也添加到好友的列表
        friend = new Friend();
        friend.setUserId(uid);
        friend.setFriendId(me.getId());
        friend.setBuildTime(new Date());
        int typeId = friendRequestService.findByUidFromId(me.getId(), uid).getType();
        friend.setTypeId(typeId);
        friendService.save(friend);
        //更新自己的消息盒子记录
        FriendRequest friendRequest = friendRequestService.findByUidFromId(me.getId(), uid);
        friendRequest.setContent("您已同意添加" + userService.getUserById(uid).getNickName() + "为好友");
        friendRequest.setIsRead(2);
        friendRequestService.updateByPrimaryKey(friendRequest);
        System.out.println("uid: " + uid + "from_group: " + from_group + "group: " + group);
        SNSResponse snsResponse = new SNSResponse();
        snsResponse.setMessage(me.getNickName() + "同意添加好友");
        snsResponse.setType("friendRequest");
        snsResponse.setStatus(1);
        snsResponse.setAvatar(me.getAvator());
        snsResponse.setUserName(me.getNickName());
        snsResponse.setTypeId(typeId);
        System.out.println("typeId: " + typeId);
        snsResponse.setId(me.getId());
        snsResponse.setSign(me.getSign());
        LLWS.sendMessage(String.valueOf(uid), JSON.toJSONString(snsResponse));
        return "{\"code\": 0, \"myGroup\": " + mygroup + ", \"userId\": " + me.getId() + "}";
    }

    @RequestMapping(value = "refuseFriend", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String refuseFriend(HttpSession session, int uid) throws IOException {
        User me = (User) session.getAttribute("user");
        //更新自己的消息盒子记录
        FriendRequest friendRequest = friendRequestService.findByUidFromId(me.getId(), uid);
        friendRequest.setContent("您已拒绝添加" + userService.getUserById(uid).getNickName() + "为好友");
        friendRequest.setIsRead(3);
        friendRequestService.updateByPrimaryKey(friendRequest);
        SNSResponse snsResponse = new SNSResponse();
        snsResponse.setMessage(me.getNickName() + "拒绝添加好友");
        snsResponse.setType("friendRequest");
        snsResponse.setStatus(2);
        LLWS.sendMessage(String.valueOf(uid), JSON.toJSONString(snsResponse));
        return "{\"code\": 0}";
    }

    @RequestMapping(value = "sendRequest", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String sendFriendRequest(HttpSession session, int uid, int type, String remark) throws IOException {
        User me  = (User) session.getAttribute("user");
        SNSResponse snsResponse = new SNSResponse();
        if(friendService.isFriend(me.getId(), uid)){
            snsResponse.setMessage("不能重复添加好友");
            snsResponse.setStatus(1);
            return JSON.toJSONString(snsResponse);
        }else {
            FriendRequest fr = friendRequestService.findByUidFromId(uid, me.getId());
            if (fr == null) {
                //留着
            } else {
                friendRequestService.deleteFriendRequest(fr);
            }
            fr = new FriendRequest();
            fr.setContent("申请添加你为好友");
            fr.setUid(uid);
            fr.setFromId(me.getId());
            fr.setFromGroup(0);
            fr.setType(type);
            fr.setRemark(remark);
            fr.setHref(null);
            fr.setIsRead(1);
            fr.setTime(new Date());
            friendRequestService.saveFriendRequest(fr);
            snsResponse.setMessage(me.getNickName() + "请求添加好友");
            snsResponse.setType("friendRequest");
            snsResponse.setStatus(0);
            LLWS.sendMessage(String.valueOf(uid), JSON.toJSONString(snsResponse));
            return "{\"message\": \"已发送好友申请\", \"status\": 0}";
        }
    }
}
