package me.albert.amazingbot.events.notice.other;

import me.albert.amazingbot.events.notice.NoticeEvent;
import me.albert.amazingbot.objects.info.DeviceInfo;

public class ClientStatusChangeEvent extends NoticeEvent {

    protected DeviceInfo client;
    protected boolean online;

    public DeviceInfo getClient() {
        return client;
    }

    public boolean isOnline() {
        return online;
    }
}
