package me.albert.amazingbot.objects.info.group;

public class GroupFile {
    protected long group_id;
    protected String file_id;
    protected String file_name;
    protected int busid;
    protected long file_size;
    protected long upload_time;
    protected long dead_time;
    protected long modify_time;
    protected int download_times;
    protected long uploader;
    protected String uploader_name;

    public long getGroupID() {
        return group_id;
    }

    public String getFileID() {
        return file_id;
    }

    public String getFileName() {
        return file_name;
    }

    public int getBusid() {
        return busid;
    }

    public long getFileSize() {
        return file_size;
    }

    public long getUploadTime() {
        return upload_time;
    }

    public long getDeadTime() {
        return dead_time;
    }

    public long getModifyTime() {
        return modify_time;
    }

    public int getDownloadTimes() {
        return download_times;
    }

    public long getUploader() {
        return uploader;
    }

    public String getUploaderName() {
        return uploader_name;
    }
}
