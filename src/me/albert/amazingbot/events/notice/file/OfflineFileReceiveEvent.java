package me.albert.amazingbot.events.notice.file;

import me.albert.amazingbot.events.notice.NoticeEvent;

public class OfflineFileReceiveEvent extends NoticeEvent {

    protected long user_id;

    protected OfflineFile file;

    public long getUserID() {
        return user_id;
    }

    public OfflineFile getFile() {
        return file;
    }
}
