package me.albert.amazingbot.objects.contact;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.objects.info.group.AtAllStatus;
import me.albert.amazingbot.objects.info.group.FileInfo;
import me.albert.amazingbot.objects.info.group.GroupFileList;
import me.albert.amazingbot.objects.info.honer.GroupHonerInfo;
import me.albert.amazingbot.objects.message.EssenceMessage;
import me.albert.amazingbot.objects.message.Message;

import java.util.List;

public class Group {
    protected long group_id;
    protected String group_name;
    protected String group_memo;
    protected long create_time;
    protected int group_level;
    protected int member_count;
    protected int max_member_count;

    public AtAllStatus getAtAllStatus() {
        return Bot.getApi().getGroupAtAllStatus(group_id);
    }

    public GroupHonerInfo getHonerInfo() {
        return Bot.getApi().getGroupHonerInfo(group_id);
    }

    public List<EssenceMessage> getEssenceMsgList() {
        return Bot.getApi().getEssenceMsgList(group_id);
    }

    public long sendMsg(String msg, boolean... auto_escape) {
        return Bot.getApi().sendGroupMsg(group_id, msg, auto_escape);
    }

    /**
     * 发送群公告
     */
    public boolean sendNotice(String content) {
        return Bot.getApi().sendGroupNotice(group_id, content);
    }

    public GroupFileList getRootFileList() {
        return Bot.getApi().getGroupRootFileList(group_id);
    }

    public FileInfo getFileSystemInfo() {
        return Bot.getApi().getGroupFileSystemInfo(group_id);
    }

    public String getGroupFileURL(String fileID, int busid) {
        return Bot.getApi().getGroupFileURL(group_id, fileID, busid);
    }

    public GroupFileList getFolderFiles(String folderID) {
        return Bot.getApi().getGroupFolderFiles(group_id, folderID);
    }

    public List<Message> getMsgHistory(long messageSeq) {
        return Bot.getApi().getGroupMsgHistory(group_id, messageSeq);
    }

    public List<Member> getMemberList() {
        return Bot.getApi().getGroupMemberList(group_id);
    }

    public boolean kick(long userID, boolean rejectAddRequest) {
        return Bot.getApi().groupKick(group_id, userID, rejectAddRequest);
    }

    public boolean mute(int duration, long user_id) {
        return Bot.getApi().groupMute(group_id, user_id, duration);
    }

    public Member getMember(long userID, boolean... noCache) {
        return Bot.getApi().getMemberInfo(group_id, userID, noCache.length > 0);
    }

    public boolean toggleWholeMute(boolean enable) {
        return Bot.getApi().toggleGroupWholeMute(group_id, enable);
    }

    public boolean setAdmin(long user_id, boolean enable) {
        return Bot.getApi().setGroupAdmin(group_id, user_id, enable);
    }

    public boolean anonymousMute(Anonymous anonymous, int duration) {
        return Bot.getApi().groupAnonymousMute(group_id, anonymous, duration);
    }

    public boolean setGroupName(String name) {
        return Bot.getApi().setGroupName(group_id, name);
    }

    public boolean leaveGroup(boolean Dismiss) {
        return Bot.getApi().setGroupLeave(group_id, Dismiss);
    }

    /**
     * 设置群头像
     *
     * @param file  图片文件名
     *              file 参数支持以下几种格式：
     *              绝对路径, 例如 file:///C:\\Users\Richard\Pictures\1.png, 格式使用 file URI
     *              网络 URL, 例如 http://i1.piimg.com/567571/fdd6e7b6d93f1ef0.jpg
     *              Base64 编码, 例如 base64://iVBORw0KGgoAAAANSUhEUgAAABQAAAAVCAIAAADJt1n/AAAA...
     * @param cache 表示是否使用已缓存的文件,1使用0不使用,默认1
     *              目前这个API在登录一段时间后因cookie失效而失效, 请考虑后使用
     */
    public boolean setGroupPortrait(String file, int cache) {
        return Bot.getApi().setGroupPortrait(group_id, file, cache);
    }

    /**
     * 在不提供 folder 参数的情况下默认上传到根目录
     * 只能上传本地文件, 需要上传 http 文件的话请先调用 download_file API下载
     */
    public boolean uploadFile(String file, String name, String folder) {
        return Bot.getApi().uploadGroupFile(group_id, file, name, folder);
    }

    public int getMaxMemberCount() {
        return max_member_count;
    }

    public int getMemberCount() {
        return member_count;
    }

    public int getGroupLevel() {
        return group_level;
    }

    public long getCreateTime() {
        return create_time;
    }

    /**
     * @return 群备注
     */
    public String getGroupMemo() {
        return group_memo;
    }

    public String getGroupName() {
        return group_name;
    }

    public long getGroupID() {
        return group_id;
    }
}
