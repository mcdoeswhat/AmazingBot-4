package me.albert.amazingbot.objects.info.group;

public class AtAllStatus {
    protected boolean can_at_all;
    protected int remain_at_all_count_for_group;
    protected int remain_at_all_count_for_uin;

    public boolean canAtAll() {
        return can_at_all;
    }

    public int getRemainAtAllCountForGroup() {
        return remain_at_all_count_for_group;
    }

    public int getRemainAtAllCountForUin() {
        return remain_at_all_count_for_uin;
    }
}
