package me.albert.amazingbot.objects.contact;

import me.albert.amazingbot.bot.Bot;

public class Friend {


    protected long user_id;


    protected String nickname;

    protected String remark;

    public long sendMsg(String msg, boolean... auto_escape) {
        return Bot.getApi().sendPrivateMsg(user_id, msg, auto_escape);
    }

    public boolean delete() {
        return Bot.getApi().deleteFriend(user_id);
    }

    public String getRemark() {
        return remark;
    }

    public String getNickName() {
        return nickname;
    }

    public long getUserID() {
        return user_id;
    }
}
