package integraldemo.demo.daos;

import integraldemo.demo.models.Message;

import java.util.List;

public interface MessageDao {

    public List<Message> getMessages(int userId);
    public Message getMessageById(int messageId);
    public int createMessage(Message message);
}
