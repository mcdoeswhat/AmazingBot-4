package me.albert.amazingbot.events.notice.friend;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.events.notice.NoticeEvent;
import me.albert.amazingbot.objects.message.Message;

public class FriendRecallEvent extends NoticeEvent {

    protected long user_id;
    protected long message_id;

    public long getUserID() {
        return user_id;
    }

    public long getMessageID() {
        return message_id;
    }

    public Message getMessage() {
        return Bot.getApi().getMsg(message_id);
    }

}
