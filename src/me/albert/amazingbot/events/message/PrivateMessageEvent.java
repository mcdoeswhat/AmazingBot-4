package me.albert.amazingbot.events.message;

import me.albert.amazingbot.bot.Bot;

public class PrivateMessageEvent extends MessageReceiveEvent {

    protected int temp_source = -1;

    public int getTempSource(){
        return temp_source;
    }

    public boolean isTempMessage(){
        return temp_source != -1;
    }

    public boolean isFriendMessage() {
        return sub_type.equals("friend");
    }

    @Override
    public long response(String message, boolean... auto_escape) {
        return Bot.getApi().sendPrivateMsg(user_id, message, auto_escape);
    }
}
