package jayim.controller;

import com.alibaba.fastjson.JSON;
import jayim.model.User;
import jayim.model.UserInfo;
import jayim.service.UserInfoService;
import jayim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/19 20:19
 */

@Controller
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService service;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "search", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String searchByConditions(String name, String province, String city){
        return JSON.toJSONString(service.searchByConditions(name == null ? "" : name, province == null ? "" : province, city == null ? "" : city));
    }

    @RequestMapping(value = "getAllInfo", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getAllInfo(int id){
        return JSON.toJSONString(service.findConcreteFriendInfo(id));
    }

    @RequestMapping(value = "updateInfo", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateInfo(Integer id, String nickName, Integer gender, String email, String phone, String avator, String province, String city, String school, String uclass, Integer age){
        System.out.println(id + " " + nickName + " " + gender + " " + email + " " + phone + " " + avator + " " + province + " " + city + " " + school + " " + uclass + " " + age);
        User user = userService.getUserById(id);
        if(nickName != null && !nickName.equals("")) user.setNickName(nickName);
        if(gender != null) user.setGender(gender);
        if(email != null && !email.equals("")) user.setEmail(email);
        if(phone != null && !phone.equals("")) user.setPhone(phone);
        if(avator != null && !avator.equals(""))user.setAvator(avator);
        UserInfo userInfo = service.findMiniFriendInfo(id);
        if(province != null && !province.equals(""))userInfo.setProvince(province);
        if(city != null && !city.equals(""))userInfo.setCity(city);
        if(school != null && !school.equals(""))userInfo.setSchool(school);
        if(uclass != null && !uclass.equals(""))userInfo.setUclass(uclass);
        if(age != null)userInfo.setAge(age);
        int userStatus = userService.updateUser(user);
        int userInfoStatus = service.updateByPrimaryKey(userInfo);
        if(userStatus < 0 || userInfoStatus < 0){
            return "{\"message\": \"修改失败\", \"userStatus\": " + userStatus +", \"userInfoStatus\": " + userInfoStatus + "}";
        }else {
            return "{\"message\": \"修改成功\", \"userStatus\": " + userStatus +", \"userInfoStatus\": " + userInfoStatus + "}";
        }
    }
}
