package me.albert.amazingbot.events.notice.group;

public class GroupMemberIncreaseEvent extends GroupNoticeEvent {

    protected String sub_type;
    protected long operator_id;

    public boolean isInvite() {
        return sub_type.equals("invite");
    }

    public String getSubType() {
        return sub_type;
    }

    public long getOperatorID() {
        return operator_id;
    }
}
