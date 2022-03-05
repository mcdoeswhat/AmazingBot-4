package me.albert.amazingbot.objects.info.group;

public class JoinRequest {

    protected long request_id;
    protected long request_uin;
    protected String request_nick;
    protected String message;
    protected long group_id;
    protected String group_name;
    protected boolean checked;
    protected long actor;

    public long getRequestID() {
        return request_id;
    }

    public long getRequestUin() {
        return request_uin;
    }

    public String getRequestNick() {
        return request_nick;
    }

    public String getMessage() {
        return message;
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
