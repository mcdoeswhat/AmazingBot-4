package me.albert.amazingbot.objects.contact;

import me.albert.amazingbot.bot.Bot;

public class Member {

    protected long group_id;
    protected long user_id;
    protected String nickname;
    protected String card;
    protected String sex;
    protected int age;
    protected String area;
    protected long join_time;
    protected long last_sent_time;
    protected String level;
    protected String role;
    protected boolean unfriendly;
    protected String title;
    protected long title_expire_time;
    protected boolean card_changeable;
    protected long shut_up_timestamp;

    public long getGroupID() {
        return group_id;
    }

    public long getUserID() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCard() {
        return card;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getArea() {
        return area;
    }

    public long getJoinTime() {
        return join_time;
    }

    public long getLastSentTime() {
        return last_sent_time;
    }

    public String getLevel() {
        return level;
    }

    public String getRole() {
        return role;
    }

    public boolean isUnfriendly() {
        return unfriendly;
    }

    public String getTitle() {
        return title;
    }

    public long getTitleExpireTime() {
        return title_expire_time;
    }

    public boolean isCardChangeable() {
        return card_changeable;
    }

    public long getShutUpTimestamp() {
        return shut_up_timestamp;
    }

    public long sendMsg(String msg, boolean... auto_escape) {
        return Bot.getApi().sendPrivateMsg(user_id, group_id, msg, auto_escape);
    }


}
