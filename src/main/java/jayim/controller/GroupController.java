package jayim.controller;

import com.alibaba.fastjson.JSON;
import jayim.model.Group;
import jayim.model.GroupUser;
import jayim.model.User;
import jayim.service.GroupService;
import jayim.service.GroupUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/group")
@Controller
public class GroupController
{

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupUserService groupUserService;



    @ResponseBody
    @RequestMapping("/getMemberByGroupId")
    public String getMemberByGroupId(Integer id){
        return getDataJson(groupUserService.getMemberByGroupId(id));
    }

    /**
     * 新增群内成员
     * @param group_id
     * @param user_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/addGroupUser")
    public List<User> addGroupUser(Integer group_id,Integer user_id){
        GroupUser groupUser=new GroupUser();
        groupUser.setGroupId(group_id);
        groupUser.setUserId(user_id);
        groupUserService.save(groupUser);
        return groupUserService.getMemberByGroupId(group_id);
    }

    @ResponseBody
    @RequestMapping("/deleteGroupUser")
    public List<User> deleteGroupUser(Integer group_id,Integer user_id){
        GroupUser groupUser=new GroupUser();
        groupUser.setGroupId(group_id);
        groupUser.setUserId(user_id);
        groupUserService.deleteByGroupUser(groupUser);
        return groupUserService.getMemberByGroupId(group_id);
    }

    @ResponseBody
    @RequestMapping("/getByUserId")
    public List<Group> getByUserId(Integer user_id){
        List<Group> byUserId = groupUserService.getByUserId(user_id);
        return byUserId;

    }

    @ResponseBody
    @RequestMapping("/deleteByGroupId")
    public List<Group>  deleteByGroupId(Integer user_id,Integer group_id){
        groupUserService.deleteByGroupId(group_id);
        return groupUserService.getByUserId(user_id);
    }

    @ResponseBody
    @RequestMapping("/updateByGroupId")
    public List<Group>  updateByGroupId(String description,String group_name,Integer user_id,Integer group_id){
        Group group=new Group();
        group.setGroupName(group_name);
        group.setDescription(description);
        groupService.update(group);
        return groupUserService.getByUserId(user_id);
    }

    public String getDataJson(List<User> lists)
    {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (User user : lists)
        {
            Map<String, Object> m = new HashMap<>();
            m.put("id", user.getId());
            m.put("username", user.getNickName());
            m.put("avatar", user.getAvator());
            m.put("sign",user.getSign());
            list.add(m);
        }
        Map<String, Object> datamap = new HashMap<>();

        map.put("code",0);
        map.put("msg",'\0');
        map.put("data",datamap );
        datamap.put("list",list);
        String str=JSON.toJSONString(map);
        System.out.println(str);
        return str;
    }

}
