package me.albert.amazingbot.events.notice.notify;

import me.albert.amazingbot.events.notice.NoticeEvent;

public class NotifyEvent extends NoticeEvent {
    protected String sub_type;

    public String getSubType() {
        return sub_type;
    }

}
