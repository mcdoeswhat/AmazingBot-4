package me.albert.amazingbot.events.notice.notify;

public class GroupHonerChangeEvent extends GroupNotifyEvent {

    protected String honer_type;

    public boolean isTalkative() {
        return honer_type.equals("talkative");
    }

    public boolean isPerformer() {
        return honer_type.equals("performer");
    }

    public boolean isEmotion() {
        return honer_type.equals("emotion");
    }

    public String getHonerType() {
        return honer_type;
    }
}
