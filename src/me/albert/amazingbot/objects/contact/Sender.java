package me.albert.amazingbot.objects.contact;

public class Sender {

    protected long user_id;

    protected String nickname;

    protected String sex;

    protected int age = 0;

    protected String card;

    protected String area;

    protected String level;

    protected String role;

    protected String title;

    public long getUserID() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }

    public String getLevel() {
        return level;
    }

    public String getArea() {
        return area;
    }

    public String getCard() {
        return card;
    }
}
