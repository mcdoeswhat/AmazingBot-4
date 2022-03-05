package me.albert.amazingbot.objects.contact;

public class Stranger {

    protected long user_id;

    protected String nickname;

    protected String sex;

    protected int age;

    protected String qid;

    protected int level;

    protected int login_days;


    public int getLoginDays() {
        return login_days;
    }

    public int getLevel() {
        return level;
    }

    public String getQid() {
        return qid;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getNickname() {
        return nickname;
    }

    public long getUserID() {
        return user_id;
    }
}
