package me.albert.amazingbot.events.notice.group;

public class GroupAdminChangeEvent extends GroupNoticeEvent {

    protected String sub_type;

    public boolean isSet() {
        return sub_type.equals("set");
    }

    public String getSubType() {
        return sub_type;
    }

}
