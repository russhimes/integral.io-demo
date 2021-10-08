package integraldemo.demo.controller;

import integraldemo.demo.daos.MessageDao;
import integraldemo.demo.daos.UserDao;
import integraldemo.demo.models.Message;
import integraldemo.demo.models.User;
import integraldemo.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    MessageDao messageDao;
    @Autowired
    UserDao userDao;
    @Autowired
    LoginService loginService;

    private User user;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(@RequestBody String username) {
        this.user = userDao.getUserByUsername(username);
        System.out.println(user);
        loginService.setCurrentUser(user);
        return user;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messages.addAll(messageDao.getMessages(user.getUserId()));
        for (int userId : user.getFollowedUsers()) {
            messages.addAll(messageDao.getMessages(userId));
        }
        return messages;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public int createMessage(@RequestBody Message message) {
        return messageDao.createMessage(message);
    }

}
