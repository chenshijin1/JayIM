package jayim.service.impl;

import java.util.List;
import com.github.pagehelper.PageInfo;
import jayim.dao.FriendTypeMapper;
import jayim.model.Friend;
import jayim.model.FriendType;
import jayim.model.User;
import jayim.service.FriendTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;


/**
 * 好友分组
 */
@Service
public class FriendTypeServiceImpl extends BaseServiceImpl<FriendType> implements FriendTypeService {

	@Autowired
	private FriendTypeMapper friendTypeMapper;

	@Override
	public Mapper<FriendType> getMapper() {
		return friendTypeMapper;
	}

	@Override
	public Integer getFriendCountByTypeId(int userId, int typeId)
	{
		return friendTypeMapper.getFriendCountByTypeId(userId,typeId);
	}

	@Override
	public List<FriendType> getFriendTypeByUserId(int userId) {
		return friendTypeMapper.getFriendTypeByUserId(userId);
	}

	@Override
	public List<FriendType> getFriendCountByUserId(int userId) {
		return null;
	}

	@Override
	public long getOnlineCountByTypeId(int id) {
		return 0;
	}

	@Override
	public PageInfo<User> getFriendByTypeIdPage(int id, int pageNum, int pageSize) {
		return null;
	}

	@Override
	public List<User> getFriendByTypeId(int id) {
		return null;
	}

	@Override
	public List<FriendType> getFriendByUserId(int userId) {
		return null;
	}

	@Override
	public int getByUserId(int userId) {
		return 0;
	}

	@Override
	public FriendType getByUserIdTypeName(int userId, String typeName) {
		return friendTypeMapper.getByUserIdTypeName(userId, typeName);
	}

	@Override
	public int updateBatchToOtherType(List<Friend> friendList, int toTypeId) {
		return 0;
	}

	@Override
	public int updateToOtherType(int userId, int friendId, int toTypeId) {
		return 0;
	}


	@Override
	public List<FriendType> searchMyFriendGroupByType(Integer userId, String searchStr) {
		return null;
	}

	@Override
	public List<FriendType> getFriendTypeListByUserId(int userId)
	{
		return friendTypeMapper.getFriendTypeListByUserId(userId);
	}

	@Override
	public FriendType getFriendTypeById(int id) {
		return friendTypeMapper.getFriendTypeById(id);
	}

	@Override
	public void updateFriendType(int typeId, int userId, int friendId) {
		friendTypeMapper.updateFriendType(typeId, userId, friendId);
	}

	@Override
	public int delById(Object id) {
		return friendTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<FriendType> getFriendTypeByUserName(int userId) {
		return friendTypeMapper.getFriendTypeByUserName(userId);
	}

	@Override
	public List<FriendType> findFriendTypeByUserIdFixed(Integer userId) {
		return friendTypeMapper.getFriendTypeByUserIdFixed(userId);
	}
}
