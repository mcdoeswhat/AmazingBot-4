package me.albert.amazingbot.events.notice.group;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.objects.contact.Member;
import org.jetbrains.annotations.Nullable;

public class GroupMemberIncreaseEvent extends GroupNoticeEvent {

    protected String sub_type;
    protected long operator_id;

    public boolean isInvite() {
        return sub_type.equals("invite");
    }

    public String getSubType() {
        return sub_type;
    }

    public long getOperatorID() {
        return operator_id;
    }

    public @Nullable Member getMember(boolean... noCache) {
        return Bot.getApi().getMemberInfo(group_id, user_id, noCache.length > 0);
    }

    public boolean kick(boolean rejectAddRequest) {
        return Bot.getApi().groupKick(group_id, user_id, rejectAddRequest);
    }

    public boolean mute(int duration) {
        return Bot.getApi().groupMute(group_id, user_id, duration);
    }

}
