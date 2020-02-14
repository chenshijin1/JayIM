package jayim.controller;

import com.alibaba.fastjson.JSON;
import jayim.model.FriendMessage;
import jayim.model.User;
import jayim.service.FriendMessageService;
import jayim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/friendMessage")
public class FriendMessageController
{

    @Autowired
    private FriendMessageService friendMessageService;


    @Autowired
    private UserService userService;
    /**
     * 用于查询用户的所有聊天记录
     * @param toId
     * @return
     */
    @RequestMapping("/findAll")
    public @ResponseBody
    String findAll(HttpSession session,Integer toId){
        System.out.println("进入findall");
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> list= new ArrayList<>();
        User user =(User) session.getAttribute("user");

        List<FriendMessage> friendMessage = friendMessageService.findFriendMessage(user.getId(), toId);
        for (int i=0;i<friendMessage.size();i++){
            Map<String, Object> data = new HashMap<>();
            //根据id查询对应的用户
            FriendMessage res=friendMessage.get(i);
            User from = userService.getUserById(res.getFromUserId());
            data.put("username",from.getNickName());
            data.put("id",from.getId());
            data.put("avatar",from.getAvator());
            data.put("timestamp",res.getSendTime());
            data.put("content",res.getContent());
            list.add(data);
            System.out.println(res.getContent());
        }

        map.put("code",0);
        map.put("msg",'\0');
        map.put("data",list);
        if (list.size()==0){
            map.put("msg","暂无聊天记录");
        }
        String str=JSON.toJSONString(map);
        System.out.println(str);
        return str;
    }
}
