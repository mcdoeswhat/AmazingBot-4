package me.albert.amazingbot.listeners;

import me.albert.amazingbot.AmazingBot;
import me.albert.amazingbot.events.message.GroupMessageEvent;
import me.albert.amazingbot.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Date;

public class NewPlayer implements Listener {

    @EventHandler
    public void msgCheck(GroupMessageEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(AmazingBot.getInstance(), () -> {
            if (!Utils.hasGroup(e.getGroupID())) {
                return;
            }
            String serverName = AmazingBot.getInstance().getConfig().getString("server_name");
            String label = AmazingBot.getInstance().getConfig().getString("function.new_player")
                    .replace("%server%", serverName);
            if (e.getMsg().equalsIgnoreCase(label)) {
                int i = 0;
                for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                    Date a = new Date(System.currentTimeMillis());
                    Date b = new Date(p.getFirstPlayed());
                    if (Utils.isSameDay(a, b)) {
                        i++;
                    }
                }
                e.response(serverName + "今日新玩家数量： " + i);
            }
        });

    }
}
