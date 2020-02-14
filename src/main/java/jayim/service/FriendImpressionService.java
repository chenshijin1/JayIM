package jayim.service;

import jayim.model.FriendImpression;

import java.util.List;

public interface FriendImpressionService extends BaseService<FriendImpression>
{
    List<FriendImpression> getUserFriendImpressionList(Integer to_user_id);
}
