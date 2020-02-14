package jayim.controller;

import com.alibaba.fastjson.JSON;
import jayim.model.Friend;
import jayim.model.FriendType;
import jayim.service.FriendService;
import jayim.service.FriendTypeService;
import jayim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/friendType")
public class FriendTypeController
{
    @Autowired
    FriendTypeService friendTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;



    @RequestMapping("findAll")
    public @ResponseBody
    String findAll(Integer user_id){
        return getDataJson(friendTypeService.getFriendTypeListByUserId(user_id),user_id);

    }

    @RequestMapping("/add")
    public @ResponseBody
    String addFriendType(Model model,String type_name,Integer user_id,Integer is_default){

        if (type_name!=null&&type_name.length()>150){
            model.addAttribute("msg", "数据太长！！！！！！");
            return getDataJson(friendTypeService.getFriendTypeListByUserId(user_id),user_id);

        }
        FriendType type=new FriendType();
        type.setTypeName(type_name);
        type.setUserId(user_id);
        type.setIsDefault(is_default);
        friendTypeService.save(type);
        //返回当前分组
        return getDataJson(friendTypeService.getFriendTypeListByUserId(user_id),user_id);

    }


    //删除类型
    @RequestMapping("/delete")
    public @ResponseBody
    String deleteFriendType(Integer id,Integer user_id){
        friendTypeService.deleteById(id);
        return getDataJson(friendTypeService.getFriendTypeListByUserId(user_id),user_id);

    }

    //修改类型
    @RequestMapping("/update")
    public @ResponseBody
    String updateFriendType(Model model,Integer id,String type_name,Integer user_id){

        if (type_name!=null&&type_name.length()>150){
            model.addAttribute("msg", "数据太长！！！！！！");
            return getDataJson(friendTypeService.getFriendTypeListByUserId(user_id),user_id);

        }
        FriendType type=new FriendType();
        type.setTypeName(type_name);
        type.setId(id);
        friendTypeService.update(type);
        return getDataJson(friendTypeService.getFriendTypeListByUserId(user_id),user_id);
    }

    @RequestMapping(value = "showFriendType", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String showFriendType(String username){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int id = userService.getUserIdByUserName(username);
        List<FriendType> friendTypeList = friendTypeService.getFriendTypeListByUserId(id);
        for(int j = 0; j < friendTypeList.size(); j++){
            sb.append("{\"type_id\": " + friendTypeList.get(j).getId() + ", \"type_name\": \"" + friendTypeList.get(j).getTypeName() + "\", \"is_default\": " + friendTypeList.get(j).getIsDefault() + ", \"user_id\": {");
            List<Integer> friendIds = friendService.getFriendByUserIdTypeId(id, friendTypeList.get(j).getId());
            for(int i = 0; i < friendIds.size(); i++){
                sb.append("\"" + (i + 1) + "\": " + userService.getUserNameByUserId(friendIds.get(i)) + "\"");
                if(i != friendIds.size() - 1){
                    sb.append(",");
                }else{
                    sb.append("}");
                }
            }
            sb.append("}");
            if(j != friendTypeList.size() - 1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }



    public String getDataJson(List<FriendType> lists,int user_id){
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> list= new ArrayList<>();
        for (FriendType type:lists){
            //在这里设置好友数目
            int count=friendTypeService.getFriendCountByTypeId(user_id,type.getId());
            type.setFriendCount(count);
            Map<String, Object> m = new HashMap<>();
            m.put("id",type.getId());
            m.put("type_name",type.getTypeName());
            m.put("is_default",type.getIsDefault());
            m.put("friend_count",count);
            list.add(m);
        }

        map.put("data",list);
        return JSON.toJSONString(map);
    }
}
