package jayim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jayim.dao.FriendMapper;
import jayim.model.Friend;
import jayim.model.User;
import jayim.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 好友Service
 * @author jay
 */
@Service
public class FriendServiceImpl extends BaseServiceImpl<Friend> implements FriendService {

	@Autowired
	private FriendMapper friendMapper;

	@Override
	public Mapper<Friend> getMapper() {
		return friendMapper;
	}

	@Override
	public List<User> searchFriendByFriendId(int friendId) {
		return null;
	}

	@Override
	public List<User> searchFriendByRemarkName(String remarkName) {
		return null;
	}

	@Override
	public boolean isFriend(int userId, int friendId) {
		Example example=new Example(Friend.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId",userId);
		criteria.andEqualTo("friendId",friendId);
		List<Friend> friends = friendMapper.selectByExample(example);
		if (friends.size() == 0)return false;
		return true;
	}

	@Override
	public int delFriend(int userId, int friendId) {
		Example example=new Example(Friend.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId",userId);
		criteria.andEqualTo("friendId",friendId);
		return friendMapper.deleteByExample(example);
	}

	@Override
	public int addFriend(int fromId, int toId, int typeId) {
		Friend friend=new Friend();
		friend.setUserId(fromId);
		friend.setFriendId(toId);
		friend.setTypeId(typeId);
		return friendMapper.insert(friend);

	}

	@Override
	public int getFriendCounts(int userId) {
		return 0;
	}

	@Override
	public List<String> getFriendsId(int userId) {
		return null;
	}

	@Override
	public List<Integer> getFriendByUserIdTypeId(int userId, int typeId) {
		return friendMapper.getFriendByUserIdTypeId(userId, typeId);
	}

	@Override
	public int moveFriend(int userId, int friendId, int toTypeId) {
		return friendMapper.moveFriend(userId, friendId, toTypeId);
	}

	@Override
	public List<Map<String, Long>> getFriendCityCountByUserId(Integer userId)
	{


		List<Map<String, Object>> friendCityCountByUserId = friendMapper.getFriendCityCountByUserId(userId);
		List<Map<String,Long>> list=new ArrayList<>();
		Map <String,Long> res;
		for (Map<String,Object> map:friendCityCountByUserId){
			res=new HashMap<>();
			res.put((String)map.get("city"),(Long)map.get("COUNT(*)"));
			list.add(res);
		}
		return list;
	}

	@Override
	public List<Integer> findFriendsByUserId(Integer userId) {
		return friendMapper.findFriendsByUserId(userId);
	}
}
