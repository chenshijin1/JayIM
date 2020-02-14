package jayim.dao;

import jayim.model.Group;
import jayim.model.User;
import tk.mybatis.mapper.common.Mapper;

public interface GroupMapper extends Mapper<Group> {
    Integer getGroupMasterId(int groupId);
}