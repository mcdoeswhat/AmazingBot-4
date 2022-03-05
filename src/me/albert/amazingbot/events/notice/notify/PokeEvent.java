package me.albert.amazingbot.events.notice.notify;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.objects.contact.Group;

public class PokeEvent extends NotifyEvent {
    protected long sender_id;
    protected long user_id;
    protected long target_id;
    protected long group_id;

    public long getSenderID() {
        return sender_id;
    }

    public long getUserID() {
        return user_id;
    }

    public long getTargetID() {
        return target_id;
    }

    public long getGroupID() {
        return group_id;
    }

    public Group getGroup(boolean... noCache) {
        return Bot.getApi().getGroupInfo(group_id, noCache.length > 0);
    }
}
