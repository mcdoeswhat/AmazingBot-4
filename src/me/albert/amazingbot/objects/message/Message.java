package me.albert.amazingbot.objects.message;

import me.albert.amazingbot.objects.contact.Anonymous;
import me.albert.amazingbot.objects.contact.Sender;

public class Message {

    protected Anonymous anonymous;

    protected int font;

    protected long group_id;

    protected long user_id;

    protected long real_id;

    protected long self_id;

    protected String message;

    protected String raw_message;

    protected long message_id;

    protected long message_seq;

    protected String message_type;

    protected String sub_type;

    protected Sender sender;

    protected long time;


    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    public Sender getSender() {
        return sender;
    }

    public long getRealID() {
        return real_id;
    }

    public long getMessageID() {
        return message_id;
    }

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public int getFont() {
        return font;
    }

    public long getGroupID() {
        return group_id;
    }

    public long getUserID() {
        return user_id;
    }

    public long getSelfID() {
        return self_id;
    }

    public String getRawMessage() {
        return raw_message;
    }

    public long getMessageSeq() {
        return message_seq;
    }

    public String getMessageType() {
        return message_type;
    }

    public String getSubType() {
        return sub_type;
    }
}
