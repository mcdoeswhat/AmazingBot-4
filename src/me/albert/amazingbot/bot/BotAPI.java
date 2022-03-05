package me.albert.amazingbot.bot;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.albert.amazingbot.AmazingBot;
import me.albert.amazingbot.database.MySQL;
import me.albert.amazingbot.objects.ApiAction;
import me.albert.amazingbot.objects.contact.*;
import me.albert.amazingbot.objects.info.*;
import me.albert.amazingbot.objects.info.group.AtAllStatus;
import me.albert.amazingbot.objects.info.group.FileInfo;
import me.albert.amazingbot.objects.info.group.GroupFileList;
import me.albert.amazingbot.objects.info.group.GroupSystemMessage;
import me.albert.amazingbot.objects.info.honer.GroupHonerInfo;
import me.albert.amazingbot.objects.info.ocr.ImageOCR;
import me.albert.amazingbot.objects.info.status.BotStatus;
import me.albert.amazingbot.objects.message.EssenceMessage;
import me.albert.amazingbot.objects.message.ForwardMessage;
import me.albert.amazingbot.objects.message.Image;
import me.albert.amazingbot.objects.message.Message;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BotAPI {

    private final BotClient client;

    public BotAPI(BotClient client) {
        this.client = client;
    }

    @Deprecated
    public static JsonObject constructAction(String action, Object... params) {
        JsonObject data = new JsonObject();
        data.addProperty("action", action);
        JsonObject param = new JsonObject();
        for (int i = 0; i < params.length; i += 2) {
            Object key = params[i];
            Object value = params[i + 1];
            if (value instanceof JsonElement) {
                param.add(key.toString(), (JsonElement) value);
                continue;
            }
            param.addProperty(key.toString(), value.toString());
        }
        data.add("params", param);
        return data;
    }

    public long sendGroupMsg(Long groupID, String msg, boolean... auto_escape) {
        long message_id = 0;
        JsonElement data = new ApiAction("send_group_msg")
                .param("group_id", groupID)
                .param("message", msg)
                .param("auto_escape", auto_escape.length > 0).requestAndGetData();
        if (data != null) {
            message_id = data.getAsJsonObject().get("message_id").getAsLong();
        }
        return message_id;
    }

    public long sendPrivateMsg(Long userID, String msg, boolean... auto_escape) {
        long message_id = 0;
        JsonElement data = new ApiAction("send_private_msg")
                .param("user_id", userID)
                .param("message", msg)
                .param("auto_escape", auto_escape.length > 0).requestAndGetData();
        if (data != null) {
            message_id = data.getAsJsonObject().get("message_id").getAsLong();
        }
        return message_id;
    }

    public boolean recallMessage(long messageID) {
        return new ApiAction("delete_msg")
                .param("message_id", messageID)
                .requestAndGetStatus();
    }

    public boolean deleteFriend(long friendID) {
        return new ApiAction("delete_friend")
                .param("friend_id", friendID)
                .requestAndGetStatus();
    }

    public long sendPrivateMsg(Long userID, Long groupID, String msg, boolean... auto_escape) {
        long message_id = 0;
        JsonElement data = new ApiAction("send_private_msg")
                .param("user_id", userID)
                .param("message", msg)
                .param("group_id", groupID)
                .param("auto_escape", auto_escape.length > 0).requestAndGetData();
        if (data != null) {
            message_id = data.getAsJsonObject().get("message_id").getAsLong();
        }
        return message_id;
    }

    public Message getMsg(long messageID) {
        JsonElement data = new ApiAction("get_msg")
                .param("message_id", messageID).requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, Message.class);
        }
        return null;
    }

    public ForwardMessage getForwardMessage(String id) {
        JsonElement data = new ApiAction("get_forward_msg")
                .param("message_id", id).requestAndGetData();
        if (data != null) {
            return new ForwardMessage(data.getAsJsonObject().get("messages").getAsJsonArray());
        }
        return null;
    }

    public Image getImage(String file) {
        JsonElement data = new ApiAction("get_image")
                .param("file", file).requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, Image.class);
        }
        return null;
    }

    public boolean groupKick(long groupID, long userID, boolean reject_add_request) {
        return new ApiAction("set_group_kick")
                .param("group_id", groupID)
                .param("user_id", userID)
                .param("reject_add_request", reject_add_request)
                .requestAndGetStatus();
    }

    public boolean groupMute(long groupID, long userID, int duration) {
        return new ApiAction("set_group_ban")
                .param("group_id", groupID)
                .param("user_id", userID)
                .param("duration", duration)
                .requestAndGetStatus();
    }

    public boolean toggleGroupWholeMute(long groupID, boolean enable) {
        return new ApiAction("set_group_whole_ban")
                .param("group_id", groupID)
                .param("enable", enable)
                .requestAndGetStatus();
    }

    public boolean setGroupAdmin(long groupID, long userID, boolean enable) {
        return new ApiAction("set_group_admin")
                .param("group_id", groupID)
                .param("user_id", userID)
                .param("enable", enable)
                .requestAndGetStatus();
    }

    public boolean groupAnonymousMute(long groupID, Anonymous anonymous, int duration) {
        return new ApiAction("set_group_anonymous_ban")
                .param("group_id", groupID)
                .param("anonymous_flag", anonymous.getFlag())
                .param("duration", duration)
                .requestAndGetStatus();
    }

    public void sendRawMsg(String msg) {
        client.send(msg);
    }

    public JsonObject sendRawData(JsonObject data) {
        int timeout = AmazingBot.getInstance().getConfig().getInt("main.timeout");
        return client.send(data, timeout);
    }


    public boolean setGroupCard(Long groupID, Long userID, String card) {
        return new ApiAction("set_group_card")
                .param("user_id", userID)
                .param("group_id", groupID)
                .param("card", card)
                .requestAndGetStatus();
    }

    public boolean setGroupName(Long groupID, String name) {
        return new ApiAction("set_group_card")
                .param("group_id", groupID)
                .param("group_name", name)
                .requestAndGetStatus();
    }

    /**
     * 退出群
     * @param groupID 群号
     * @param isDismiss 是否解散(默认false,为群主时true会解散群)
     */
    public boolean setGroupLeave(Long groupID, boolean isDismiss) {
        return new ApiAction("set_group_leave")
                .param("group_id", groupID)
                .param("is_dismiss", isDismiss)
                .requestAndGetStatus();
    }

    public boolean setGroupSpecialTitle(Long groupID, long userID, String title) {
        return new ApiAction("set_group_special_title")
                .param("group_id", groupID)
                .param("user_id", userID)
                .param("special_title", title)
                .requestAndGetStatus();
    }

    public boolean setFriendAddRequest(String flag, boolean approve, String remark) {
        return new ApiAction("set_friend_add_request")
                .param("flag", flag)
                .param("approve", approve)
                .param("remark", remark)
                .requestAndGetStatus();
    }

    public boolean setGroupAddRequest(String flag, String sub_type, boolean approve, String reason) {
        return new ApiAction("set_group_add_request")
                .param("flag", flag)
                .param("approve", approve)
                .param("sub_type", sub_type)
                .param("reason", reason)
                .requestAndGetStatus();
    }

    public LoginInfo getLoginInfo() {
        JsonElement data = new ApiAction("get_login_info").requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, LoginInfo.class);
        }
        return null;
    }

    public QiDianInfo getQiDianInfo() {
        JsonElement data = new ApiAction("qidian_get_account_info").requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, QiDianInfo.class);
        }
        return null;
    }

    public Group getGroupInfo(long groupID, boolean nocache) {
        JsonElement data = new ApiAction("get_group_info")
                .param("group_id", groupID)
                .param("no_cache", nocache).requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, Group.class);
        }
        return null;
    }

    public Stranger getStrangerInfo(long userID, boolean noCache) {
        JsonElement data = new ApiAction("get_stranger_info")
                .param("user_id", userID)
                .param("no_cache", noCache).requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, Stranger.class);
        }
        return null;
    }

    public Member getMemberInfo(long groupID, long user_id, boolean no_cache) {
        JsonElement data = new ApiAction("get_group_member_info")
                .param("user_id", user_id)
                .param("group_id", groupID)
                .param("no_cache", no_cache).requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, Member.class);
        }
        return null;
    }

    public List<Member> getGroupMemberList(long group_id) {
        List<Member> members = new ArrayList<>();
        JsonElement data = new ApiAction("get_group_member_list")
                .param("group_id", group_id)
                .requestAndGetData();
        if (data != null) {
            for (JsonElement jsonElement : data.getAsJsonArray()) {
                members.add(new Gson().fromJson(jsonElement, Member.class));
            }
        }
        return members;
    }

    public List<Friend> getFriendList() {
        List<Friend> friends = new ArrayList<>();
        JsonElement data = new ApiAction("get_group_list").requestAndGetData();
        if (data != null) {
            for (JsonElement friend : data.getAsJsonArray()) {
                friends.add(new Gson().fromJson(friend, Friend.class));
            }
        }
        return friends;
    }

    public List<Group> getGroupList() {
        List<Group> groups = new ArrayList<>();
        JsonElement data = new ApiAction("get_group_list").requestAndGetData();
        if (data != null) {
            for (JsonElement group : data.getAsJsonArray()) {
                groups.add(new Gson().fromJson(group, Group.class));
            }
        }
        return groups;
    }

    public GroupHonerInfo getGroupHonerInfo(long group_id) {
        JsonElement data = new ApiAction("get_group_honor_info")
                .param("group_id", group_id)
                .param("type", "all")
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, GroupHonerInfo.class);
        }
        return null;
    }

    public long sendForwardMessage(Long groupID, ForwardMessage forwardMessage) {
        long message_id = 0;
        JsonElement data = new ApiAction("send_group_forward_msg")
                .param("group_id", groupID)
                .param("messages", forwardMessage.getMessages())
                .requestAndGetData();
        if (data != null) {
            message_id = data.getAsJsonObject().get("message_id").getAsLong();
        }
        return message_id;
    }

    public VersionInfo getVersionInfo() {
        JsonElement data = new ApiAction("get_version_info")
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, VersionInfo.class);
        }
        return null;
    }

    public void restartBot(long delay) {
        new ApiAction("set_restart")
                .param("delay", delay)
                .request();
    }

    /**
     * 设置群头像
     */
    public boolean setGroupPortrait(long group_id, String file, int cache) {
        return new ApiAction("set_group_portrait")
                .param("group_id", group_id)
                .param("file", file)
                .param("cache", cache)
                .requestAndGetStatus();
    }

    public List<String> getWordSlices(String content) {
        List<String> result = new ArrayList<>();
        JsonElement data = new ApiAction("get_word_slices")
                .param("content", content).requestAndGetData();
        if (data != null) {
            for (JsonElement s : data.getAsJsonObject().get("slices").getAsJsonArray()) {
                result.add(s.getAsString());
            }
        }
        return result;
    }

    public ImageOCR getImageOCR(String imageID) {
        JsonElement data = new ApiAction("ocr_image")
                .param("image", imageID)
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, ImageOCR.class);
        }
        return null;
    }

    public boolean uploadGroupFile(long group_id, String file, String name, String folder) {
        return new ApiAction("upload_groupfile")
                .param("group_id", group_id)
                .param("file", file)
                .param("name", name)
                .param("folder", folder)
                .requestAndGetStatus();
    }

    public FileInfo getGroupFileSystemInfo(long group_id) {
        JsonElement data = new ApiAction("get_group_file_system_info")
                .param("group_id", group_id).requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, FileInfo.class);
        }
        return null;
    }

    public GroupSystemMessage getGroupSystemMessage() {
        JsonElement data = new ApiAction("get_group_system_msg")
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, GroupSystemMessage.class);
        }
        return null;
    }

    public GroupFileList getGroupRootFileList(long group_id) {
        JsonElement data = new ApiAction("get_group_root_files")
                .param("group_id", group_id)
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, GroupFileList.class);
        }
        return null;
    }

    public GroupFileList getGroupFolderFiles(long group_id, String folder_id) {
        JsonElement data = new ApiAction("get_group_files_by_folder")
                .param("group_id", group_id)
                .param("folder_id", folder_id)
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, GroupFileList.class);
        }
        return null;
    }

    public String getGroupFileURL(long group_id, String file_id, int busid) {
        JsonElement data = new ApiAction("get_group_file_url")
                .param("group_id", group_id)
                .param("file_id", file_id)
                .param("busid", busid)
                .requestAndGetData();
        if (data != null) {
            return data.getAsJsonObject().get("url").getAsString();
        }
        return null;
    }

    public BotStatus getBotStatus() {
        JsonElement data = new ApiAction("get_status")
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, BotStatus.class);
        }
        return null;
    }

    public String downloadFile(String url, int thread_count, String headers) {
        JsonElement data = new ApiAction("download_file")
                .param("url", url)
                .param("thread_count", thread_count)
                .param("headers", headers)
                .requestAndGetData();
        if (data != null) {
            return data.getAsJsonObject().get("file").getAsString();
        }
        return null;
    }

    public List<DeviceInfo> getOnlineClients() {
        List<DeviceInfo> deviceInfoList = new ArrayList<>();
        JsonElement data = new ApiAction("get_online_clients")
                .requestAndGetData();
        if (data != null) {
            for (JsonElement jsonElement : data.getAsJsonObject().get("clients").getAsJsonArray()) {
                DeviceInfo deviceInfo = new Gson().fromJson(jsonElement, DeviceInfo.class);
                deviceInfoList.add(deviceInfo);
            }
        }
        return deviceInfoList;
    }

    public List<Message> getGroupMsgHistory(long group_id, long message_seq) {
        List<Message> history = new ArrayList<>();
        JsonElement data = new ApiAction("get_group_msg_history")
                .param("group_id", group_id)
                .param("message_seq", message_seq)
                .requestAndGetData();
        if (data != null) {
            for (JsonElement jsonElement : data.getAsJsonObject().get("messages").getAsJsonArray()) {
                history.add(new Gson().fromJson(jsonElement, Message.class));
            }
        }
        return history;
    }

    public boolean sendGroupNotice(long group_id, String content) {
        return new ApiAction("_send_group_notice")
                .param("group_id", group_id)
                .param("content", content)
                .requestAndGetStatus();
    }

    public boolean setEssenceMsg(long message_id) {
        return new ApiAction("set_essence_msg")
                .param("message_id", message_id)
                .requestAndGetStatus();
    }

    public boolean deleteEssenceMsg(long message_id) {
        return new ApiAction("delete_essence_msg")
                .param("message_id", message_id)
                .requestAndGetStatus();
    }

    public VIPInfo getVIPInfo(long user_id) {
        JsonElement data = new ApiAction("_get_vip_info")
                .param("user_id", user_id)
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, VIPInfo.class);
        }
        return null;
    }

    public AtAllStatus getGroupAtAllStatus(long group_id) {
        JsonElement data = new ApiAction("get_group_at_all_remain")
                .param("group_id", group_id)
                .requestAndGetData();
        if (data != null) {
            return new Gson().fromJson(data, AtAllStatus.class);
        }
        return null;
    }

    public List<EssenceMessage> getEssenceMsgList(long group_id) {
        List<EssenceMessage> messages = new ArrayList<>();
        JsonElement data = new ApiAction("get_essence_msg_list")
                .param("group_id", group_id)
                .requestAndGetData();
        if (data != null) {
            for (JsonElement jsonElement : data.getAsJsonArray()) {
                messages.add(new Gson().fromJson(jsonElement, EssenceMessage.class));
            }
        }
        return messages;
    }

    public int checkURLSafe(String url) {
        JsonElement data = new ApiAction("check_url_safely")
                .param("url", url)
                .requestAndGetData();
        if (data != null) {
            return data.getAsJsonObject().get("level").getAsInt();
        }
        return 2;
    }

    public boolean setModelShow(String model, String model_show) {
        return new ApiAction("_set_model_show")
                .param("model", model)
                .param("model_show", model_show)
                .requestAndGetStatus();
    }


    public boolean canSendImage() {
        JsonElement data = new ApiAction("can_send_image")
                .requestAndGetData();
        if (data != null) {
            return data.getAsJsonObject().get("yes").getAsBoolean();
        }
        return false;
    }

    public boolean canSendVoice() {
        JsonElement data = new ApiAction("can_send_record")
                .requestAndGetData();
        if (data != null) {
            return data.getAsJsonObject().get("yes").getAsBoolean();
        }
        return false;
    }


    public void setBind(Long userID, UUID uuid) {
        if (MySQL.ENABLED) {
            MySQL.savePlayer(userID, uuid.toString());
            return;
        }
        FileConfiguration data = AmazingBot.getData().getConfig();
        data.set(String.valueOf(userID), uuid.toString());
        AmazingBot.getData().save();
    }

    public UUID getPlayer(Long userID) {
        if (MySQL.ENABLED) {
            return MySQL.getPlayer(userID);
        }
        UUID uuid = null;
        FileConfiguration data = AmazingBot.getData().getConfig();
        if (data.getString(String.valueOf(userID)) != null) {
            uuid = UUID.fromString(data.getString(String.valueOf(userID)));
        }
        return uuid;
    }

    public void removePlayer(UUID playerID) {
        if (MySQL.ENABLED) {
            MySQL.removePlayer(playerID.toString());
            return;
        }
        Long userID = null;
        FileConfiguration data = AmazingBot.getData().getConfig();
        for (String key : data.getConfigurationSection("").getKeys(false)) {
            if (data.getString(key).equalsIgnoreCase(playerID.toString())) {
                userID = Long.parseLong(key);
            }
        }
        data.set(String.valueOf(userID), null);
    }

    public void removePlayer(Long userID) {
        if (MySQL.ENABLED) {
            MySQL.removePlayer(userID);
            return;
        }
        FileConfiguration data = AmazingBot.getData().getConfig();
        data.set(String.valueOf(userID), null);
    }

    public Long getUser(UUID playerID) {
        if (MySQL.ENABLED) {
            return MySQL.getQQ(playerID.toString());
        }
        Long userID = null;
        FileConfiguration data = AmazingBot.getData().getConfig();
        for (String key : data.getConfigurationSection("").getKeys(false)) {
            if (data.getString(key).equalsIgnoreCase(playerID.toString())) {
                return Long.parseLong(key);
            }
        }
        return userID;
    }


}
