package jayim.service;

import jayim.model.User;
import jayim.model.UserInfo;
import jayim.vo.SNSConcreteUserInfo;
import jayim.vo.SNSSearchResultItem;

import java.util.List;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/16 21:46
 */
public interface UserInfoService {
    UserInfo findMiniFriendInfo(int uid);

    SNSConcreteUserInfo findConcreteFriendInfo(int uid);

    List<SNSSearchResultItem> searchByConditions(String name, String province, String city);

    int updateByPrimaryKey(UserInfo userInfo);

    int insertUserInfo(UserInfo userInfo);
}
