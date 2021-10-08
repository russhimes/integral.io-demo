package integraldemo.demo.models;

import java.util.List;

public class User {

    private int userId;
    private String username;
    private List<Integer> followedUsers;

    public User(int userId, String username, List<Integer> followedUsers) {
        this.userId = userId;
        this.username = username;
        this.followedUsers = followedUsers;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(List<Integer> followedUsers) {
        this.followedUsers = followedUsers;
    }
}
