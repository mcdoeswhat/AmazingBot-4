package me.albert.amazingbot.events.message;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.objects.contact.Anonymous;
import me.albert.amazingbot.objects.contact.Group;
import me.albert.amazingbot.objects.contact.Member;
import me.albert.amazingbot.objects.message.ForwardMessage;

public class GroupMessageEvent extends MessageReceiveEvent {

    protected Long group_id;

    protected Anonymous anonymous;

    public boolean isAnonymous() {
        return getAnonymous() != null;
    }

    public long response(ForwardMessage message) {
        return Bot.getApi().sendForwardMessage(getGroupID(), message);
    }

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public Long getGroupID() {
        return group_id;
    }

    public Group getGroup(boolean... noCache) {
        return Bot.getApi().getGroupInfo(group_id, noCache.length > 0);
    }

    public Member getMember(boolean... noCache) {
        return Bot.getApi().getMemberInfo(group_id, user_id, noCache.length > 0);
    }

    public boolean mute(int duration) {
        return Bot.getApi().groupMute(group_id, user_id, duration);
    }

    public boolean kick(boolean reject_add_request) {
        return Bot.getApi().groupKick(group_id, user_id, reject_add_request);
    }

    public boolean setGroupCard(String card) {
        return Bot.getApi().setGroupCard(group_id, user_id, card);
    }

    public boolean setAsEssenceMsg() {
        return Bot.getApi().setEssenceMsg(this.message_id);
    }

    public boolean setGroupSpecialTitle(String title) {
        return Bot.getApi().setGroupSpecialTitle(group_id, user_id, title);
    }

    @Override
    public long response(String message, boolean... auto_escape) {
        return Bot.getApi().sendGroupMsg(group_id, message, auto_escape);
    }
}
