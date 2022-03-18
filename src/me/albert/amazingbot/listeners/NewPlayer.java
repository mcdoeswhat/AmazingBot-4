package me.albert.amazingbot.listeners;

import me.albert.amazingbot.AmazingBot;
import me.albert.amazingbot.events.message.GroupMessageEvent;
import me.albert.amazingbot.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Date;

public class NewPlayer implements Listener {

    @EventHandler
    public void msgCheck(GroupMessageEvent event) {
        if (!Utils.hasGroup(event.getGroupID())) {
            return;
        }
        FileConfiguration config = AmazingBot.getInstance().getConfig();
        String serverName = config.getString("server_name");
        String label = config.getString("function.new_player")
                .replace("%server%", serverName);
        if (event.getMsg().equalsIgnoreCase(label)) {
            int i = 0;
            for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                Date a = new Date(System.currentTimeMillis());
                Date b = new Date(player.getFirstPlayed());
                if (Utils.isSameDay(a, b)) {
                    i++;
                }
            }
            event.response(serverName + "今日新玩家数量： " + i);
        }
    }
}
