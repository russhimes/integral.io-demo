package integraldemo.demo.daos;

import integraldemo.demo.models.User;

public interface UserDao {

    public User getUserById(int userId);
    public User getUserByUsername(String username);
    public void followUserByUsername(int userId, String username);
}
