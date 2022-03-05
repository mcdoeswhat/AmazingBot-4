package me.albert.amazingbot.events.notice.group;

public class GroupCardChangeEvent extends GroupNoticeEvent {

    protected String card_new;
    protected String card_old;

    public String getCardNew() {
        return card_new;
    }

    public String getCardOld() {
        return card_old;
    }

}
