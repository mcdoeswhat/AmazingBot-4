package me.albert.amazingbot.events.notice;

import me.albert.amazingbot.events.ABEvent;

public class NoticeEvent extends ABEvent {
    protected String notice_type;

    public String getNoticeType() {
        return notice_type;
    }
}
