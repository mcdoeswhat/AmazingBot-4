package me.albert.amazingbot.events.request;

import me.albert.amazingbot.bot.Bot;

public class FriendRequestEvent extends RequestEvent {

    protected long user_id;
    protected String comment;
    protected String flag;

    public long getUserID() {
        return user_id;
    }

    public String getComment() {
        return comment;
    }

    public String getFlag() {
        return flag;
    }

    public boolean approve(boolean approve,String remark){
        return Bot.getApi().setFriendAddRequest(flag,approve,remark);
    }
}
