package me.albert.amazingbot.events.notice.group;

public class GroupMuteEvent extends GroupNoticeEvent {

    protected String sub_type;
    protected long operator_id;
    protected long duration;

    public boolean isMute() {
        return sub_type.equals("ban");
    }

    public String getSubType() {
        return sub_type;
    }

    public long getOperatorID() {
        return operator_id;
    }

    public long getDuration() {
        return duration;
    }
}
