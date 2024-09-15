package lp.caio.model;

public class UserView {
    private long id;
    private long userId;
    private long messageId;
    private boolean read;

    public UserView(long userId, long messageId, boolean read) {
        this.userId = userId;
        this.messageId = messageId;
        this.read = read;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", User ID: " + userId + 
               ", Message ID: " + messageId + ", Read: " + read;
    }   
}
