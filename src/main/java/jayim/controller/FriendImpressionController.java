package jayim.controller;

import com.alibaba.fastjson.JSON;
import jayim.model.FriendImpression;
import jayim.service.FriendImpressionService;
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
@RequestMapping("/friendImpression")
public class FriendImpressionController
{
    @Autowired
    FriendImpressionService friendImpressionService;

    @RequestMapping("/findAll")
    public @ResponseBody
    String findAll(Integer user_id)
    {
        return getDataJson(friendImpressionService.getUserFriendImpressionList(user_id));
    }

    @RequestMapping("/add")
    public @ResponseBody
    String addFriendImpression(Model model,Integer from_user_id, Integer to_user_id,String content)
    {
        if (content!=null&&content.length()>150){
            model.addAttribute("msg", "数据太长！！！！！！");
            return getDataJson(friendImpressionService.getUserFriendImpressionList(to_user_id));
        }
        FriendImpression friendImpression=new FriendImpression();
        friendImpression.setFromUserId(from_user_id);
        friendImpression.setToUserId(to_user_id);
        friendImpression.setContent(content);
        friendImpressionService.save(friendImpression);
        //返回当前分组
        return getDataJson(friendImpressionService.getUserFriendImpressionList(to_user_id));
    }

    @RequestMapping("/delete")
    public @ResponseBody
    String deleteFriendType(Integer id, Integer to_user_id)
    {
        friendImpressionService.deleteById(id);
        return getDataJson(friendImpressionService.getUserFriendImpressionList(to_user_id));

    }

    @RequestMapping("/update")
    public @ResponseBody
    String updateFriendType(Model model,Integer id, String content, Integer to_user_id)
    {
        if (content!=null&&content.length()>150){
            model.addAttribute("msg", "数据太长！！！！！！");
            return getDataJson(friendImpressionService.getUserFriendImpressionList(to_user_id));
        }

        FriendImpression friendImpression=new FriendImpression();
        friendImpression.setId(id);
        friendImpression.setContent(content);

        friendImpressionService.update(friendImpression);
        return getDataJson(friendImpressionService.getUserFriendImpressionList(to_user_id));

    }

    public String getDataJson(List<FriendImpression> lists)
    {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (FriendImpression impression : lists)
        {
            Map<String, Object> m = new HashMap<>();
            m.put("id", impression.getId());
            m.put("from_user_id", impression.getFromUserId());
            m.put("content", impression.getContent());
            list.add(m);
        }

        map.put("data", list);
        return JSON.toJSONString(map);
    }
}
