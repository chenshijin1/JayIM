package jayim.service.impl;

import jayim.dao.FriendImpressionMapper;
import jayim.model.Friend;
import jayim.model.FriendImpression;
import jayim.service.FriendImpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class FriendImpressionServiceImpl extends BaseServiceImpl<FriendImpression> implements FriendImpressionService
{

    @Autowired
    FriendImpressionMapper friendImpressionMapper;

    @Override
    public Mapper<FriendImpression> getMapper()
    {
        return friendImpressionMapper;
    }

    @Override
    public List<FriendImpression> getUserFriendImpressionList(Integer to_user_id)
    {
        Example example=new Example(FriendImpression.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("toUserId",to_user_id);

        return friendImpressionMapper.selectByExample(example);
    }
}
