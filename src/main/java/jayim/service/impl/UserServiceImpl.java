package jayim.service.impl;

import jayim.dao.FriendTypeMapper;
import jayim.dao.UserInfoMapper;
import jayim.model.FriendType;
import jayim.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jayim.dao.UserMapper;
import jayim.model.User;
import jayim.service.UserService;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl  extends BaseServiceImpl<User> implements UserService{
	 @Autowired
     private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private FriendTypeMapper friendTypeMapper;


     @Override
     public Mapper<User> getMapper() {
        return userMapper;
     }

     public User getUserById(int userId) {  
        return userMapper.selectByPrimaryKey(userId);  
     }  
     
     public User matchUser(String userName,String password) {
    	 User record=new User();
    	 record.setUserName(userName);
    	 record.setPassword(password);
         return userMapper.selectOne(record);
     }

    @Override
    public Integer register(User user)
    {
        User user1 = matchUser(user.getUserName(), user.getPassword());
        if (user1!=null)return -1;
        else {
            user.setAvator("/images/default.png");
            userMapper.insert(user);
            UserInfo userInfo=new UserInfo();
            userInfo.setUserId(user.getId());
            userInfo.setProvince("未填写");
            userInfo.setCity("未填写");
            userInfo.setSchool("未填写");
            userInfo.setUclass("未填写");
            userInfo.setAge(0);
            userInfoMapper.insertSelective(userInfo);
            FriendType friendType = new FriendType();
            friendType.setTypeName("好友");
            friendType.setUserId(user.getId());
            friendType.setBuildTime(new Date());
            friendType.setIsDefault(1);
            friendTypeMapper.insert(friendType);
            return 0;
        }
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int getUserIdByUserName(String userName) {
        return userMapper.getUserIdByUserName(userName);
    }

    @Override
    public String getUserNameByUserId(int userId) {
        return userMapper.getUserNameByUserId(userId);
    }
}
