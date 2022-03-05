package me.albert.amazingbot.objects.info.group;

public class InviteRequest {
    protected long request_id;
    protected long invitor_uin;
    protected String inviter_nick;
    protected long group_id;
    protected String group_name;
    protected boolean checked;
    protected long actor;

    public long getRequestID() {
        return request_id;
    }

    public long getInvitorUin() {
        return invitor_uin;
    }

    public String getInviterNick() {
        return inviter_nick;
    }

    public long getGroupID() {
        return group_id;
    }

    public String getGroupName() {
        return group_name;
    }

    public boolean isChecked() {
        return checked;
    }

    public long getActor() {
        return actor;
    }
}
