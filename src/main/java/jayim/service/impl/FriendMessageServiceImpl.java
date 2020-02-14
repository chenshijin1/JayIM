package jayim.service.impl;

import jayim.dao.FriendMessageMapper;
import jayim.model.FriendMessage;
import jayim.service.FriendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class FriendMessageServiceImpl extends BaseServiceImpl<FriendMessage> implements FriendMessageService
{

    @Autowired
    private FriendMessageMapper friendMessageMapper;
    @Override
    public Mapper<FriendMessage> getMapper()
    {
        return friendMessageMapper;
    }

    @Override
    public List<FriendMessage> findFriendMessage(Integer fromId, Integer toId)
    {
        return friendMessageMapper.findFriendMessage(fromId,toId,toId,fromId);
    }
}
