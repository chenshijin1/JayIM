package jayim.service.impl;

import jayim.dao.FriendRequestMapper;
import jayim.model.FriendRequest;
import jayim.model.FriendRequestExample;
import jayim.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/15 10:53
 */

@Service
public class FriendRequestImpl implements FriendRequestService {
    @Autowired
    private FriendRequestMapper mapper;

    @Override
    public void saveFriendRequest(FriendRequest friendRequest) {
        mapper.insert(friendRequest);
    }

    @Override
    public List<FriendRequest> findFriendRequestFromByPage(int fromId, int page) {
        return mapper.findFriendRequestFromByPage(fromId, (page - 1) * 5, 5);
    }

    @Override
    public List<FriendRequest> findFriendRequestByPage(int uid, int page) {
        return mapper.findFriendRequestByPage(uid, (page - 1) * 5, 5);
    }

    @Override
    public List<FriendRequest> findFriendRequestAll() {
        FriendRequestExample example = new FriendRequestExample();
        FriendRequestExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return mapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(FriendRequest record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public FriendRequest findByUidFromId(int uid, int fromId) {
        FriendRequestExample example = new FriendRequestExample();
        FriendRequestExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andFromIdEqualTo(fromId);
        List<FriendRequest> list = mapper.selectByExample(example);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public void delAll() {
        FriendRequestExample example = new FriendRequestExample();
        mapper.deleteByExample(example);
    }

    @Override
    public int deleteFriendRequest(FriendRequest friendRequest) {
        return mapper.deleteByPrimaryKey(friendRequest.getId());
    }
}
