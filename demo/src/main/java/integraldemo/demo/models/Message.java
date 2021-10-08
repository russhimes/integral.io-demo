package integraldemo.demo.models;

public class Message {

    private String message;
    private int messageId;
    private int userId;

    public Message(String message, int messageId, int userId) {
        this.message = message;
        this.messageId = messageId;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
