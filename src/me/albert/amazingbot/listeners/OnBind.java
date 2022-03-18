package me.albert.amazingbot.listeners;

import me.albert.amazingbot.AmazingBot;
import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.events.message.GroupMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class OnBind implements Listener {

    private static final HashMap<UUID, Long> binds = new HashMap<>();
    private static final HashSet<Long> tempUser = new HashSet<>();

    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        FileConfiguration config = AmazingBot.getInstance().getConfig();
        if (!config.getBoolean("groups." + event.getGroupID() + ".enable_bind")) {
            return;
        }
        String bd = config.getString("bd");
        if (event.getMsg().startsWith(bd)) {
            String userName = event.getMsg().substring(bd.length()).trim();
            if (Bukkit.getPlayerExact(userName) == null || isVanished(Bukkit.getPlayerExact(userName))) {
                event.response("该玩家不在线!");
                return;
            }
            if (tempUser.contains(event.getUserID())) {
                event.response("1小时内仅允许一次此操作!");
                return;
            }
            Player p = Bukkit.getPlayerExact(userName);
            sendBind(event.getUserID(), p);
            event.response("请在游戏内根据提示完成验证!");
            tempUser.add(event.getUserID());
            Bukkit.getScheduler().runTaskLater(AmazingBot.getInstance(), () -> tempUser.remove(event.getUserID()), 20 * 60 * 60);
        }
    }

    private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }


    @EventHandler(ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (!binds.containsKey(uuid)) {
            return;
        }
        if (event.getMessage().startsWith("确认绑定 ")) {
            String user = event.getMessage().substring(5);
            Long userID = binds.get(uuid);
            if (!user.equalsIgnoreCase(String.valueOf(userID))) {
                return;
            }
            event.setCancelled(true);
            binds.remove(uuid);
            Bot.getApi().setBind(userID, uuid);
            event.getPlayer().sendMessage("§a绑定成功!");
        }
    }

    private void sendBind(Long userID, Player player) {
        List<String> messages = AmazingBot.getInstance().getConfig().getStringList("messages.bind");
        for (String s : messages) {
            s = s.replace("&", "§")
                    .replace("%user%", String.valueOf(userID));
            player.sendMessage(s);
        }
        binds.put(player.getUniqueId(), userID);
        Bukkit.getScheduler().runTaskLater(AmazingBot.getInstance(), () -> binds.remove(player.getUniqueId()), 20 * 60);

    }

}
