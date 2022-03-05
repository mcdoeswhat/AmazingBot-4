package me.albert.amazingbot.objects.info.group;

public class FileInfo {

    protected int file_count;
    protected int limit_count;
    protected long used_space;
    protected long total_space;

    public int getFileCount() {
        return file_count;
    }

    public int getLimitCount() {
        return limit_count;
    }

    public long getUsedSpace() {
        return used_space;
    }

    public long getTotalSpace() {
        return total_space;
    }

}
