package jayim.dao;

import jayim.model.FriendMessage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FriendMessageMapper extends Mapper<FriendMessage>
{
    List<FriendMessage> findFriendMessage(
        @Param("a") Integer from_user_id,
        @Param("b")Integer to_user_id,
        @Param("c")Integer to_user_id1,
        @Param("d")Integer from_user_id1);
}