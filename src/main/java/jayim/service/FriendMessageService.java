package jayim.service;

import jayim.model.FriendMessage;

import java.util.List;

public interface FriendMessageService extends BaseService<FriendMessage>
{
    List<FriendMessage> findFriendMessage(Integer fromId,Integer toId);
}
