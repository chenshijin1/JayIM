package jayim.service.impl;

import java.util.List;
import jayim.dao.GroupUserMapper;
import jayim.model.Group;
import jayim.model.GroupUser;
import jayim.model.User;
import jayim.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * 群成员
 * @author jay
 */
@Service
public class GroupUserServiceImpl extends BaseServiceImpl<GroupUser> implements GroupUserService {

	@Autowired
	GroupUserMapper groupUserMapper;


	@Override
	public Mapper<GroupUser> getMapper() {
		return groupUserMapper;
	}

	@Override
	public List<User> getMemberByGroupId(int groupId)
	{
		return groupUserMapper.getMemberByGroupId(groupId);
	}

	/**
	 * 返回userid list
	 * @param groupId
	 * @return
	 */
	@Override
	public List<String> getSimpleMemberByGroupId(int groupId) {
		return groupUserMapper.getSimpleMemberByGroupId(groupId);
	}

	@Override
	public List<Group> getByUserId(int userId) {
		return 	groupUserMapper.getByUserId(userId);
	}

	@Override
	public int batchSave(List<GroupUser> guserList) {
		return 0;
	}

	@Override
	public int deleteByGroupId(int groupId) {
		return groupUserMapper.deleteByPrimaryKey(groupId);
	}

	@Override
	public boolean hasUser(int groupId) {
		return false;
	}

	@Override
	public List<GroupUser> getByGroupId(int groupId) {
		return groupUserMapper.getByGroupId(groupId);
	}

	@Override
	public List<GroupUser> getNewMemberByGroupId(int groupId, int userId) {
		return null;
	}

	@Override
	public int deleteByGroupUser(GroupUser groupUser)
	{
		return groupUserMapper.delete(groupUser);
	}

}
