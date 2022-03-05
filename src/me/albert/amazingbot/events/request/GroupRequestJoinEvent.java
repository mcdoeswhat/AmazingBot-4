package me.albert.amazingbot.events.request;

import me.albert.amazingbot.bot.Bot;

public class GroupRequestJoinEvent extends RequestEvent {

    protected long group_id;
    protected long user_id;
    protected String comment;
    protected String sub_type;
    protected String flag;

    public long getGroupID() {
        return group_id;
    }

    public long getUserID() {
        return user_id;
    }

    public String getComment() {
        return comment;
    }

    public boolean isInvite(){
        return sub_type.equals("invite");
    }

    public String getFlag() {
        return flag;
    }

    public boolean approve(boolean approve,String reason){
        return Bot.getApi().setGroupAddRequest(flag,sub_type,approve,reason);
    }

    public String getSubType() {
        return sub_type;
    }
}
