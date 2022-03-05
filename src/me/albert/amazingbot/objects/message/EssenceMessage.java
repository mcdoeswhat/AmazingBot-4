package me.albert.amazingbot.objects.message;

public class EssenceMessage {

    protected long sender_id;
    protected String sender_nick;
    protected long sender_time;
    protected long operator_id;
    protected String operator_nick;
    protected long operator_time;
    protected long message_id;

    public long getSenderID() {
        return sender_id;
    }

    public String getSenderNick() {
        return sender_nick;
    }

    public long getSenderTime() {
        return sender_time;
    }

    public long getOperatorID() {
        return operator_id;
    }

    public String getOperatorNick() {
        return operator_nick;
    }

    public long getOperatorTime() {
        return operator_time;
    }

    public long getMessageID() {
        return message_id;
    }
}
