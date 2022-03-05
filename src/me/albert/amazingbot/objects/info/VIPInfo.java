package me.albert.amazingbot.objects.info;

public class VIPInfo {

    protected long user_id;
    protected String nickname;
    protected long level;
    protected float level_speed;
    protected String vip_level;
    protected long vip_growth_speed;
    protected long vip_growth_total;

    public long getUserID() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public long getLevel() {
        return level;
    }

    public float getLevelSpeed() {
        return level_speed;
    }

    public String getVipLevel() {
        return vip_level;
    }

    public long getVipGrowthSpeed() {
        return vip_growth_speed;
    }

    public long getVipGrowthTotal() {
        return vip_growth_total;
    }
}
