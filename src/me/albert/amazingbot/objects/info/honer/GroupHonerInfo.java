package me.albert.amazingbot.objects.info.honer;

import java.util.List;

public class GroupHonerInfo {

    protected long group_id;

    protected Talkative current_talkative;

    protected List<HonerInfo> talkative_list;

    protected List<HonerInfo> performer_list;

    protected List<HonerInfo> legend_list;

    protected List<HonerInfo> strong_newbie_list;

    protected List<HonerInfo> emotion_list;

    public long getGroupID() {
        return group_id;
    }

    public Talkative getCurrentTalkative() {
        return current_talkative;
    }

    public List<HonerInfo> getTalkativeList() {
        return talkative_list;
    }

    public List<HonerInfo> getPerformerList() {
        return performer_list;
    }

    public List<HonerInfo> getLegendList() {
        return legend_list;
    }

    public List<HonerInfo> getStrongNewbieList() {
        return strong_newbie_list;
    }

    public List<HonerInfo> getEmotionList() {
        return emotion_list;
    }

}
