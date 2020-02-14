package jayim.dao;

import java.util.List;

import jayim.model.FriendType;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface FriendTypeMapper extends Mapper<FriendType> {
	// 获取好友分组列表-后期功能
	List<FriendType> getFriendTypeByUserId(int userId);

	List<FriendType> getFriendTypeListByUserId(int userId);


	FriendType getFriendTypeById(@Param("id") int id);

	FriendType getByUserIdTypeName(@Param("userId") int userId, @Param("typeName") String typeName);

	void updateFriendType(@Param("typeId")int typeId, @Param("userId")int userId, @Param("friendId")int friendId);

	List<FriendType> getFriendTypeByUserName(@Param("userId")int userId);

	Integer getFriendCountByTypeId(int user_id, int type_id);

	List<FriendType> getFriendTypeByUserIdFixed(@Param("userId")Integer userId);

}