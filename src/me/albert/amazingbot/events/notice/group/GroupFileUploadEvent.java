package me.albert.amazingbot.events.notice.group;

public class GroupFileUploadEvent extends GroupNoticeEvent {

    protected File file;

    public File getFile() {
        return file;
    }

    public class File {

        protected String id;
        protected String name;
        protected long size;
        protected int busid;

        public String getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }

        public int getBusid() {
            return busid;
        }
    }
}
