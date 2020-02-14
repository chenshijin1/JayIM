package jayim.service;

import jayim.model.User;

public interface UserService
{

    User getUserById(int userId);

    User matchUser(String userName, String password);

    Integer register(User user);

    int updateUser(User user);

    int getUserIdByUserName(String userName);

    String getUserNameByUserId(int userId);
}
