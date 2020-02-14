package jayim.dao;

import jayim.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
	int getUserIdByUserName(@Param("userName") String userName);

	String getUserNameByUserId(@Param("id")int id);
}