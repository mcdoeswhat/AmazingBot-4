package me.albert.amazingbot.events.notice.group;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.events.notice.NoticeEvent;
import me.albert.amazingbot.objects.contact.Group;

public class GroupNoticeEvent extends NoticeEvent {

    protected long group_id;

    protected long user_id;

    public long getGroupID() {
        return group_id;
    }

    public long getUserID() {
        return user_id;
    }

    public Group getGroup(boolean... noCache){
        return Bot.getApi().getGroupInfo(group_id,noCache.length > 0);
    }

}
