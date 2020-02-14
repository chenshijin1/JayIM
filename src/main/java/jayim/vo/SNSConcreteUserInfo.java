package jayim.vo;


import jayim.model.FriendImpression;
import jayim.model.User;
import jayim.model.UserInfo;

import java.util.List;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/16 21:48
 */
public class SNSConcreteUserInfo {
    private UserInfo userInfo;
    private User user;
    private List<FriendImpression> friendImpressions;

    public List<FriendImpression> getFriendImpression() {
        return friendImpressions;
    }

    public void setFriendImpression(List<FriendImpression> friendImpressions) {
        this.friendImpressions = friendImpressions;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
