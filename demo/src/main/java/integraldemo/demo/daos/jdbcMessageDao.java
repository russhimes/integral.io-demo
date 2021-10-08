package integraldemo.demo.daos;

import integraldemo.demo.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class jdbcMessageDao implements MessageDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Message> getMessages(int userId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            messages.add(mapMessage(results));
        }
        return messages;
    }
    @Override
    public Message getMessageById(int messageId) {
        String sql = "SELECT * FROM messages WHERE message_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, messageId);
        if (results.next()) {
            return mapMessage(results);
        }
        return null;
    }

    @Override
    public int createMessage(Message message) {
        String sql = "INSERT INTO messages (user_id, message_text) " +
                "VALUES (?,?) RETURNING message_id";

        return jdbcTemplate.queryForObject(sql, Integer.class, message.getUserId(), message.getMessage());
    }

    private Message mapMessage(SqlRowSet rowSet) {
        int messageId = rowSet.getInt("message_id");
        int userId = rowSet.getInt("user_id");
        String message = rowSet.getString("message_text");

        return new Message(message, messageId, userId);
    }
}
