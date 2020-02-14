package jayim.controller;

import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import jayim.model.Friend;
import jayim.model.FriendType;
import jayim.model.Group;
import jayim.service.FriendService;
import jayim.service.FriendTypeService;
import jayim.service.GroupUserService;
import jayim.utils.RedisUtils;
import jayim.vo.*;
import jayim.ws.LLWS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import jayim.model.User;
import jayim.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("index")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	private FriendTypeService friendTypeService;
	@Autowired
	private GroupUserService groupUserService;
	@Autowired
	private FriendService friendService;

	private static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping("main")
	public String index() {
		return "main";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	/**
	 * 用户登录
	 * @author jay
	 * @param model
	 * @param userName   用户名
	 * @param password   密码
	 */
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public String loginCheck(HttpSession session,Model model, String userName, String password) {
		// 是否存在用户
		//判断是否已经有用户登陆
		Object user1 = session.getAttribute("user");
		String url="login";
		if (user1!=null){
			model.addAttribute("msg", "一个浏览器只能登陆一个用户，请换个浏览器！");
			return url;
		}

		User user = userService.matchUser(userName,password);
		if (user!=null) {
			session.setAttribute("user",user);
			logger.info("用户："+user.getNickName()+" : "+user.getUserName()+"  登录成功!");
			return "main";
		} 
		model.addAttribute("msg", "用户名或密码错误，请重新输入!");
		logger.error("userName:"+userName+" password:"+password+"  登录失败!");
		return url;
	}
	
	/**
	 * 退出登录
	 * @author jay
	 */
	@RequestMapping("logout")
	public String logout(SessionStatus sessionStatus, HttpSession session) {
		sessionStatus.setComplete();
		session.invalidate();
		return "redirect:/";
	}


	/**
	 * 注册
	 * @author jay
	 */
	@RequestMapping("register")
	public String register(User user,Model model) {
		if(user!=null)
		{
			Integer res = userService.register(user);
			if (res < 0)
			{
				model.addAttribute("msg", "用户名以存在，请重新输入");
				logger.error("userName:" + user.getUserName() + " password:" + user.getPassword() + "  注册失败!");
				return "toRegisterPage";
			}
			else
			{
				model.addAttribute("msg", "注册成功，请登陆");
				return "redirect:/index/login";
			}
		}else{
			return "redirect:/index/login";
		}
	}


	@RequestMapping("toRegisterPage")
	public String toRegisterPage() {
		return "";//还没写
	}
	/**
	 * 给layim提供初始化数据服务，包括个人信息、好友列表信息、群组列表信息
	 * @param userId
	 * @author jay
	 */
	@RequestMapping(value = "getInitList", produces = "text/plain; charset=utf-8")
	public @ResponseBody String getInitList(int userId) {
		User u=userService.getUserById(userId);
		SNSUser mine=new SNSUser();
		mine.setId(u.getId());
		if(u.getAvator()==null || u.getAvator().equals("")){
			mine.setAvatar("images/avatar/default.png");
		}else{
			mine.setAvatar(u.getAvator());
		}
		mine.setSign(u.getSign());
		mine.setUsername(u.getNickName());
		//获取redis中的用户在线状态
		String redisKey=userId+"_status";
		RedisUtils.set(redisKey, "online");
		mine.setStatus("online");
		SNSInit snsinit =new SNSInit();
		SNSdata data=new SNSdata();
		data.setMine(mine);
		snsinit.setData(data);
		List<SNSFriend> snsFriendList = new ArrayList<>();
//		List<FriendType> list = friendTypeService.getFriendTypeByUserId(userId);
		List<FriendType> list = friendTypeService.findFriendTypeByUserIdFixed(userId);
		System.out.println("list: " + JSON.toJSONString(list));
		if(list==null){
			int id=friendTypeService.getByUserId(userId);  //如果没有默认分组，则初始化分组
			SNSFriend snsFriend = new SNSFriend();
			snsFriend.setGroupname("好友");
			snsFriend.setOnline(0);
			snsFriend.setId(id);
			snsFriendList.add(snsFriend);
		}else{
			for(int i = 0; i < list.size(); i++){
				SNSFriend snsFriend = new SNSFriend();
				snsFriend.setGroupname(list.get(i).getTypeName());
				snsFriend.setId(list.get(i).getId());
				List<SNSUser> snsUserList = new ArrayList<>();
				for(Integer uid : friendService.getFriendByUserIdTypeId(userId, list.get(i).getId())){
					User tmp = userService.getUserById(uid);
					SNSUser snsUser = new SNSUser();
					snsUser.setUsername(tmp.getNickName());
					snsUser.setId(tmp.getId());
					snsUser.setAvatar(tmp.getAvator());
					snsUser.setSign(tmp.getSign());
					redisKey=tmp.getId() + "_status";
					if(RedisUtils.exists(redisKey)){
						snsUser.setStatus(RedisUtils.get(redisKey).toString());
					}else{
						snsUser.setStatus("offline");
					}
					snsUserList.add(snsUser);
				}
				snsFriend.setList(snsUserList);
				snsFriendList.add(snsFriend);
			}
			/*try{
				for(int i=0;i<list.size();i++){
					SNSFriend snsFriend = new SNSFriend();

					// friend:[]中的每个{}

					snsFriend.setGroupname(list.get(i).getTypeName());
					System.out.println("typeName: " + list.get(i).getTypeName());
					snsFriend.setId(list.get(i).getId());
					List<Friend> friendList=list.get(i).getFriends();
					List<SNSUser> snsUserList = new ArrayList<>();
					int onlineNum=0;

					// list:[]中的每个{}

					for(int j=0;j<friendList.size();j++){
						SNSUser snsUser = new SNSUser();
						snsUser.setAvatar(friendList.get(j).getFriendInfo().getAvator());
						snsUser.setSign(friendList.get(j).getFriendInfo().getSign());
						snsUser.setUsername(friendList.get(j).getFriendInfo().getNickName());
						snsUser.setId(friendList.get(j).getFriendId());
						onlineNum++;
						//获取redis中的用户在线状态
						redisKey=friendList.get(j).getFriendId()+"_status";
						if(RedisUtils.exists(redisKey)){
							snsUser.setStatus(RedisUtils.get(redisKey).toString());
						}else{
							snsUser.setStatus("offline");
						}
						snsUserList.add(snsUser);
					}



					snsFriend.setOnline(onlineNum);
					snsFriend.setList(snsUserList);
					snsFriendList.add(snsFriend);
				}
			}catch(Exception e){
				e.printStackTrace();
			}*/
		}

		data.setFriend(snsFriendList);
		//获取我加入的群的列表
		List<Group> groupList = groupUserService.getByUserId(userId);
		List<SNSGroup> glist = new ArrayList<>();
		if(groupList!=null){
			for(int k=0;k<groupList.size();k++){
				SNSGroup sgroup = new SNSGroup();
				sgroup.setGroupname(groupList.get(k).getGroupName());
				sgroup.setId(groupList.get(k).getId());
				sgroup.setAvatar(groupList.get(k).getAvator());
				glist.add(sgroup);
			}
			data.setGroup(glist);
		}
		snsinit.setData(data);
		logger.info(JSON.toJSONString(snsinit));
		return JSON.toJSONString(snsinit);
	}

	@RequestMapping(value = "getOfflineMsgFromRedis")
	public @ResponseBody JSONArray getOfflineMsgFromRedis(int userId) {
		JSONArray jsonArray = new JSONArray();
		if (RedisUtils.exists(userId + "_msg"))
		{
			Long len = RedisUtils.llen(userId + "_msg");
			while (len > 0)
			{
				jsonArray.add(RedisUtils.rpop(userId + "_msg"));
				len--;
			}
		}
		return jsonArray;
	}

	@RequestMapping(value = "updateSign", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String updateSign(Integer id, String sign){
		User user = userService.getUserById(id);
		user.setSign(sign);
		userService.updateUser(user);
		return "{message: \"修改成功\", status: 0}";
	}

	@RequestMapping(value = "updateStatus", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String updateStatus(Integer userId, String status) throws IOException {
		for(Integer friendId : friendService.findFriendsByUserId(userId)){
			LLWS.sendMessage(String.valueOf(friendId), "{\"id\": " + userId + ", \"content\": " + (status.equals("online") ? "\"online\"" : "\"offline\"") + ", " + "\"type\": " + "\"onlineStatus\"" + "}");
		}
		return "{message: \"状态更改成功\", status: 0}";
	}

}
