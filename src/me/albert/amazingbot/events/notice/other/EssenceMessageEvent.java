package me.albert.amazingbot.events.notice.other;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.events.notice.NoticeEvent;
import me.albert.amazingbot.objects.contact.Group;

public class EssenceMessageEvent extends NoticeEvent {

    protected String sub_type;
    protected long sender_id;
    protected long operator_id;
    protected long message_id;
    protected long group_id;

    public String getSubType() {
        return sub_type;
    }

    public boolean isAdd() {
        return sub_type.equals("add");
    }

    public long getSenderID() {
        return sender_id;
    }

    public long getOperatorID() {
        return operator_id;
    }

    public long getMessageID() {
        return message_id;
    }

    public long getGroupID() {
        return group_id;
    }

    public Group getGroup(boolean... noCache) {
        return Bot.getApi().getGroupInfo(group_id, noCache.length > 0);
    }

}
