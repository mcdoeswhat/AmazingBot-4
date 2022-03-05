package me.albert.amazingbot.events.notice.group;

public class GroupRecallEvent extends GroupNoticeEvent {

    protected long operator_id;

    protected long message_id;

    public long getOperatorID() {
        return operator_id;
    }

    public long getMessageID() {
        return message_id;
    }
}
