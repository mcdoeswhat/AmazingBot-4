package me.albert.amazingbot.events.notice.group;

public class GroupMemberDecreaseEvent extends GroupNoticeEvent{

    protected String sub_type;
    protected long operator_id;

    public String getSubType() {
        return sub_type;
    }

    public boolean isLeave(){
        return sub_type.equals("leave");
    }

    public boolean isKick(){
        return sub_type.equals("kick");
    }

    public boolean isKickMe(){
        return sub_type.equals("kick_me");
    }

    public long getOperatorID() {
        return operator_id;
    }
}
