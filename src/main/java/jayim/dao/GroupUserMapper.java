package jayim.dao;

import jayim.model.Group;
import jayim.model.GroupUser;
import jayim.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroupUserMapper extends Mapper<GroupUser>
{
    List<Group> getByUserId(int userId);

    List<GroupUser> getByGroupId(int groupId);

    List<User> getMemberByGroupId(Integer groupId);
    //返回用户idlist
    List<String> getSimpleMemberByGroupId(Integer groupId);
}