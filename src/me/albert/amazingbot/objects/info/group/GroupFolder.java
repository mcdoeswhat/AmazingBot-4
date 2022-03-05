package me.albert.amazingbot.objects.info.group;

public class GroupFolder {
    protected long group_id;
    protected String folder_id;
    protected String folder_name;
    protected long create_time;
    protected long creator;
    protected String creator_name;
    protected int total_file_count;

    public long getGroupID() {
        return group_id;
    }

    public String getFolderID() {
        return folder_id;
    }

    public String getFolderName() {
        return folder_name;
    }

    public long getCreateTime() {
        return create_time;
    }

    public long getCreator() {
        return creator;
    }

    public String getCreatorName() {
        return creator_name;
    }

    public int getTotalFileCount() {
        return total_file_count;
    }
}
