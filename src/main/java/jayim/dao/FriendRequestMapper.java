package jayim.dao;

import jayim.model.FriendRequest;
import jayim.model.FriendRequestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendRequestMapper {
    int countByExample(FriendRequestExample example);

    int deleteByExample(FriendRequestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FriendRequest record);

    int insertSelective(FriendRequest record);

    List<FriendRequest> selectByExample(FriendRequestExample example);

    FriendRequest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FriendRequest record, @Param("example") FriendRequestExample example);

    int updateByExample(@Param("record") FriendRequest record, @Param("example") FriendRequestExample example);

    int updateByPrimaryKeySelective(FriendRequest record);

    int updateByPrimaryKey(FriendRequest record);

    List<FriendRequest> findFriendRequestByPage(@Param("uid") int uid, @Param("start") int start, @Param("size") int size);

    List<FriendRequest> findFriendRequestFromByPage(@Param("fromId") int fromId, @Param("start") int start, @Param("size") int size);

}