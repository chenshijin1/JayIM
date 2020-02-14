package jayim.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import jayim.service.FriendService;
import jayim.service.FriendTypeService;
import jayim.vo.SNSLittleFriend;
import jayim.vo.SNSResponse;
import jayim.ws.LLWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import jayim.model.User;
import jayim.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private UserService userService;
	@Autowired
    private FriendService friendService;
	@Autowired
    private FriendTypeService friendTypeService;


    @RequestMapping("/isFriend")
    @ResponseBody
    public boolean isFriend(Integer user_id,int friend_id){
        return friendService.isFriend(user_id,friend_id);
    }


    @RequestMapping(value="/init/{userId}",produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String init(HttpSession session, @PathVariable("userId")int userId){
        session.setMaxInactiveInterval(3600);
        User user = userService.getUserById(userId);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mine = new HashMap<>();
        mine.put("username", user.getNickName());
        mine.put("id", user.getId());
        mine.put("status", "online");
        mine.put("sign", user.getSign());
        mine.put("avatar", user.getAvator());
        map.put("mine", mine);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/delFriend",produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String delFriend(int userId, int friendId) throws IOException {
        int status1 = friendService.delFriend(userId, friendId);
        int status2 = friendService.delFriend(friendId, userId);
        if(status1 < 0 || status2 < 0)
            return "{\"message\": \"删除好友失败\", " + "\"status1\": " + status1 + ", \"status2\": "+ status2 + "}";
        else {
            SNSResponse response = new SNSResponse();
            response.setType("delFriend");
            response.setId(userId);
            LLWS.sendMessage(String.valueOf(friendId), JSON.toJSONString(response));
            return "{\"message\": \"删除好友成功\", " + "\"status1\": " + status1 + ", \"status2\": " + status2 + "}";
        }
    }

    @RequestMapping(value = "/moveFriend", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String moveFriend(int userId, int friendId, int toTypeId) throws IOException {
        int status = friendService.moveFriend(userId, friendId, toTypeId);
        if(status < 0)
            return "{\"message\": \"移动好友失败\", " + "\"status\": " + status + "}";
        else {
            User friend = userService.getUserById(friendId);
            SNSResponse response = new SNSResponse();
            response.setAvatar(friend.getAvator());
            response.setUserName(friend.getNickName());
            response.setTypeId(toTypeId);
            response.setId(friendId);
            response.setSign(friend.getSign());
            response.setStatus(status);
            response.setType("moveFriend");
            LLWS.sendMessage(String.valueOf(userId), JSON.toJSONString(response));
            return "{\"message\": \"移动好友成功\", " + "\"status\": " + status + ", \"avatar\": \"" + friend.getAvator() + "\", \"username\": \"" + friend.getNickName() + "\", \"typeId\": \"" + toTypeId + "\", \"sign\": \"" + friend.getSign() + "\"}";
        }
    }

    @RequestMapping(value = "/showFriends", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String showFriends(int id, int typeId){
        List<SNSLittleFriend> list = new ArrayList<>();
        for(int userId : friendService.getFriendByUserIdTypeId(id, typeId)){
            User tmp = userService.getUserById(userId);
            SNSLittleFriend littleFriend = new SNSLittleFriend();
            littleFriend.setId(tmp.getId());
            littleFriend.setAvatar(tmp.getAvator());
            littleFriend.setNickName(tmp.getNickName());
            list.add(littleFriend);
        }
        return JSON.toJSONString(list);
    }


    @ResponseBody
    @RequestMapping(value = "getFriendCityCountByUserId",produces = "application/json;charset=utf-8")
    public String getFriendCityCountByUserId(Integer user_id){
        System.out.println("Jinrucount");
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Long>> list= friendService.getFriendCityCountByUserId(user_id);
        map.put("data",list);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/test")
    public String test(){
        System.out.println("------------------------");
       return "main";
    }


}