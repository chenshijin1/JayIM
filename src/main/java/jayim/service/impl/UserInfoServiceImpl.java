package jayim.service.impl;

import jayim.dao.UserInfoMapper;
import jayim.dao.UserMapper;
import jayim.model.FriendImpression;
import jayim.model.User;
import jayim.model.UserInfo;
import jayim.model.UserInfoExample;
import jayim.service.FriendImpressionService;
import jayim.service.UserInfoService;
import jayim.vo.SNSConcreteUserInfo;
import jayim.vo.SNSSearchResultItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/17 21:39
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FriendImpressionService friendImpressionService;

    @Override
    public UserInfo findMiniFriendInfo(int uid) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUserIdEqualTo(uid);
        return mapper.selectByExample(example).get(0);
    }

    @Override
    public SNSConcreteUserInfo findConcreteFriendInfo(int uid) {
        UserInfo userInfo = findMiniFriendInfo(uid);
        User user = userMapper.selectByPrimaryKey(uid);
        user.setPassword(null);
        SNSConcreteUserInfo concreteUserInfo = new SNSConcreteUserInfo();
        concreteUserInfo.setUser(user);
        concreteUserInfo.setUserInfo(userInfo);
        concreteUserInfo.setFriendImpression(friendImpressionService.getUserFriendImpressionList(uid));
        return concreteUserInfo;
    }

    @Override
    public List<SNSSearchResultItem> searchByConditions(String name, String province, String city) {
        return mapper.searchByConditions(name, province, city);
    }

    @Override
    public int updateByPrimaryKey(UserInfo userInfo) {
        return mapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return mapper.insert(userInfo);
    }
}
