package me.albert.amazingbot.listeners;

import me.albert.amazingbot.AmazingBot;
import me.albert.amazingbot.events.message.GroupMessageEvent;
import me.albert.amazingbot.utils.command.ConsoleSender;
import me.albert.amazingbot.utils.command.ConsoleSenderLegacy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class OnCommand implements Listener {


    public static int spigot = 0;

    private static boolean isAdmin(Long userID) {
        return AmazingBot.getInstance().getConfig().getLongList("owners").contains(userID);
    }

    private static boolean hasGroup(Long groupID) {
        ConfigurationSection section = AmazingBot.getInstance().getConfig().getConfigurationSection("groups");
        for (String s : section.getKeys(false)) {
            if (s.equalsIgnoreCase(String.valueOf(groupID))) {
                return true;
            }
        }
        return false;
    }

    private static String getLabel(Long groupID) {
        if (!hasGroup(groupID)) {
            return null;
        }
        return AmazingBot.getInstance().getConfig().getString("groups." + groupID + ".command") + " ";
    }

    public static CommandSender getSender(long contactID, boolean isGroup) {
        CommandSender sender = null;
        if (spigot == 1) {
            sender = new ConsoleSenderLegacy(contactID, isGroup);
        }
        if (spigot == 2) {
            sender = new ConsoleSender(contactID, isGroup);
        }
        if (spigot == 0) {
            try {
                Class.forName("org.bukkit.command.CommandSender$Spigot");
            } catch (Exception ignored) {
                spigot = 1;
                AmazingBot.getInstance().getLogger().info("§a检测到旧版本Minecraft,启用旧版本指令信息返回...");
                sender = new ConsoleSenderLegacy(contactID, isGroup);
            }
            if (sender == null) {
                spigot = 2;
                sender = new ConsoleSender(contactID, isGroup);
            }
        }
        return sender;
    }

    @EventHandler
    public void onCommand(GroupMessageEvent event) {
        if (!isAdmin(event.getUserID())) {
            return;
        }
        String label = getLabel(event.getGroupID());
        String msg = event.getTextMessage();
        if (label == null || !msg.startsWith(label)) {
            return;
        }
        event.response("命令已提交");
        String cmd = msg.substring(label.length());
        CommandSender sender = getSender(event.getGroupID(), true);
        Bukkit.getScheduler().runTask(AmazingBot.getInstance(), () -> Bukkit.dispatchCommand(sender, cmd));
        String log = AmazingBot.getInstance().getConfig().getString("messages.log_command")
                .replace("%user%", String.valueOf(event.getUserID())).replace("%cmd%", cmd)
                .replace("&", "§");
        AmazingBot.getInstance().getLogger().info(log);
    }

    @EventHandler
    public void onSwitch(GroupMessageEvent event) {
        if (!isAdmin(event.getUserID())) {
            return;
        }
        FileConfiguration config = AmazingBot.getInstance().getConfig();
        String serverName = config.getString("server_name");
        String label = config.getString("commands.toggle_on").replace("%server%", serverName);
        String off = config.getString("commands.toggle_off");
        off = off.replace("%server%", serverName);
        if (event.getMsg().equalsIgnoreCase(off) && hasGroup(event.getGroupID())) {
            config.set("groups." + event.getGroupID(), null);
            AmazingBot.getInstance().saveConfig();
            String toggle_off = config.getString("messages.toggle_off").replace("%server%", serverName);
            event.response(toggle_off);
            return;
        }
        if (event.getMsg().startsWith(label)) {
            if (!hasGroup(event.getGroupID())) {
                config.set("groups." + event.getGroupID() + ".command", event.getMsg().substring(label.length()));
                config.set("groups." + event.getGroupID() + ".enable_bind", false);
                AmazingBot.getInstance().saveConfig();
                String toggle_on = config.getString("messages.toggle_on").replace("%server%", serverName)
                        .replace("%label%", event.getMsg().substring(label.length()));
                event.response(toggle_on);
            }
        }
    }

    @EventHandler
    public void onAddRemove(GroupMessageEvent event) {
        if (!isAdmin(event.getUserID())) {
            return;
        }
        FileConfiguration config = AmazingBot.getInstance().getConfig();
        String serverName = config.getString("server_name");
        String add = config.getString("commands.add").replace("%server%", serverName);
        String remove = config.getString("commands.remove").replace("%server%", serverName);
        String userID;
        if (event.getMsg().startsWith(add)) {
            userID = event.getMsg().substring(add.length());
            List<String> admins = config.getStringList("owners");
            admins.add(userID);
            config.set("owners", admins);
            AmazingBot.getInstance().saveConfig();
            String msg = config.getString("messages.add")
                    .replace("%server%", serverName)
                    .replace("%user%", userID);
            event.response(msg);
            return;
        }
        if (event.getMsg().startsWith(remove)) {
            userID = event.getMsg().substring(add.length());
            List<String> admins = config.getStringList("owners");
            admins.remove(userID);
            config.set("owners", admins);
            AmazingBot.getInstance().saveConfig();
            String msg = config.getString("messages.remove")
                    .replace("%server%", serverName)
                    .replace("%user%", userID);
            event.response(msg);
        }


    }

}
