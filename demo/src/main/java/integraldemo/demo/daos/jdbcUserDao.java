package integraldemo.demo.daos;

import integraldemo.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class jdbcUserDao implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        sql = "SELECT followed_user_id FROM followers WHERE follower_user_id = ?";
        SqlRowSet followed = jdbcTemplate.queryForRowSet(sql, userId);

        if (result.next()) {
            return mapUser(result, followed);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        sql = "SELECT followed_user_id FROM followers JOIN users ON " +
                "follower_user_id = user_id WHERE username = ?";
        SqlRowSet followed = jdbcTemplate.queryForRowSet(sql, username);

        if (result.next()) {
            return mapUser(result, followed);
        }
        return null;
    }

    @Override
    public void followUserByUsername(int userId, String username) {
        String getId = "SELECT user_id FROM users WHERE username = ?";
        int followedUserId = jdbcTemplate.queryForObject(getId, Integer.class, username);
        String sql = "INSERT INTO followers (follower_user_id, followed_user_id) " +
                "VALUES (?,?)";
        jdbcTemplate.update(sql, userId, followedUserId);
    }

    private User mapUser(SqlRowSet rowSet, SqlRowSet followed) {
        int userId = rowSet.getInt("user_id");
        String username = rowSet.getString("username");
        List<Integer> followedList = new ArrayList<>();
        while (followed.next()) {
            followedList.add(followed.getInt("followed_id"));
        }
        return new User(userId, username, followedList);
    }
}
