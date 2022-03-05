package me.albert.amazingbot.bot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.albert.amazingbot.events.ABEvent;
import me.albert.amazingbot.events.message.GroupMessageEvent;
import me.albert.amazingbot.events.message.MessageReceiveEvent;
import me.albert.amazingbot.events.message.PrivateMessageEvent;
import me.albert.amazingbot.events.notice.NoticeEvent;
import me.albert.amazingbot.events.notice.file.OfflineFileReceiveEvent;
import me.albert.amazingbot.events.notice.friend.FriendRecallEvent;
import me.albert.amazingbot.events.notice.group.*;
import me.albert.amazingbot.events.notice.notify.GroupHonerChangeEvent;
import me.albert.amazingbot.events.notice.notify.GroupLuckyKingEvent;
import me.albert.amazingbot.events.notice.notify.NotifyEvent;
import me.albert.amazingbot.events.notice.notify.PokeEvent;
import me.albert.amazingbot.events.notice.other.ClientStatusChangeEvent;
import me.albert.amazingbot.events.notice.other.EssenceMessageEvent;
import me.albert.amazingbot.events.request.FriendRequestEvent;
import me.albert.amazingbot.events.request.GroupRequestJoinEvent;
import me.albert.amazingbot.events.request.RequestEvent;

public class BotEventParser {

    public static Gson gson = new Gson();

    private final JsonObject object;

    public BotEventParser(JsonObject object) {
        this.object = object;
    }

    public ABEvent parseEvent() {
        ABEvent abEvent = parse(ABEvent.class);
        String post_type = abEvent.getPostType();
        switch (post_type) {
            case "message":
                return parseMessageEvent();
            case "notice":
                return parseNoticeEvent();
            case "request":
                return parseRequestEvent();
        }
        return abEvent;
    }

    public <T extends ABEvent> T parse(Class<T> eventClass) {
        return gson.fromJson(object, eventClass);
    }

    private RequestEvent parseRequestEvent() {
        RequestEvent requestEvent = parse(RequestEvent.class);
        switch (requestEvent.getRequestType()) {
            case "group":
                return parse(GroupRequestJoinEvent.class);
            case "friend":
                return parse(FriendRequestEvent.class);
        }
        return requestEvent;
    }


    private NotifyEvent parseNotifyEvent() {
        NotifyEvent notifyEvent = parse(NotifyEvent.class);
        switch (notifyEvent.getSubType()) {
            case "poke":
                return parse(PokeEvent.class);
            case "lucky_king":
                return parse(GroupLuckyKingEvent.class);
            case "honor":
                return parse(GroupHonerChangeEvent.class);
        }
        return notifyEvent;
    }

    private NoticeEvent parseNoticeEvent() {
        NoticeEvent noticeEvent = parse(NoticeEvent.class);
        String notice_type = noticeEvent.getNoticeType();
        switch (notice_type) {
            case "group_upload":
                return parse(GroupFileUploadEvent.class);
            case "group_admin":
                return parse(GroupAdminChangeEvent.class);
            case "group_decrease":
                return parse(GroupMemberDecreaseEvent.class);
            case "group_increase":
                return parse(GroupMemberIncreaseEvent.class);
            case "group_ban":
                return parse(GroupMuteEvent.class);
            case "group_recall":
                return parse(GroupRecallEvent.class);
            case "friend_recall":
                return parse(FriendRecallEvent.class);
            case "group_card":
                return parse(GroupCardChangeEvent.class);
            case "offline_file":
                return parse(OfflineFileReceiveEvent.class);
            case "client_status":
                return parse(ClientStatusChangeEvent.class);
            case "essence":
                return parse(EssenceMessageEvent.class);
            case "notify":
                return parseNotifyEvent();
        }
        return noticeEvent;
    }


    private MessageReceiveEvent parseMessageEvent() {
        MessageReceiveEvent messageReceiveEvent = parse(MessageReceiveEvent.class);
        String messageType = messageReceiveEvent.getMessageType();
        switch (messageType) {
            case "group":
                return parse(GroupMessageEvent.class);
            case "private":
                return parse(PrivateMessageEvent.class);
        }
        return messageReceiveEvent;
    }


}
