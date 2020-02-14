package jayim.service;

import jayim.model.FriendRequest;

import java.util.List;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/14 23:46
 */
public interface FriendRequestService {
    void saveFriendRequest(FriendRequest friendRequest);
    List<FriendRequest> findFriendRequestByPage(int uid, int page);
    List<FriendRequest> findFriendRequestFromByPage(int fromId, int page);
    List<FriendRequest> findFriendRequestAll();
    int updateByPrimaryKey(FriendRequest record);
    FriendRequest findByUidFromId(int uid, int fromId);
    void delAll();
    int deleteFriendRequest(FriendRequest friendRequest);
}
