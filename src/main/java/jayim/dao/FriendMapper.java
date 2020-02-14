package jayim.dao;

import jayim.model.Friend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface FriendMapper extends Mapper<Friend> {
	List<Integer> getFriendByUserIdTypeId(@Param("userId")int userId, @Param("typeId")int typeId);

	int moveFriend(@Param("userId")int userId, @Param("friendId")int friendId, @Param("toTypeId")int toTypeId);


	//[{"COUNT(*)":2,"city":"北京市"},{"COUNT(*)":1,"city":"杭州"},{"COUNT(*)":1,"city":"武汉"},{"COUNT(*)":1,"city":"深圳"}]

	List<Map<String, Object>> getFriendCityCountByUserId(Integer userId);

	List<Integer> findFriendsByUserId(@Param("userId") Integer userId);
}