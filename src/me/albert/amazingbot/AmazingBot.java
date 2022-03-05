package me.albert.amazingbot;

import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.database.MySQL;
import me.albert.amazingbot.listeners.NewPlayer;
import me.albert.amazingbot.listeners.OnBind;
import me.albert.amazingbot.listeners.OnCommand;
import me.albert.amazingbot.utils.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.atomic.AtomicBoolean;

public class AmazingBot extends JavaPlugin {
    public static AtomicBoolean async = new AtomicBoolean(true);
    private static AmazingBot instance;
    private static CustomConfig data;
    private static CustomConfig mysqlSettings;

    public static AmazingBot getInstance() {
        return instance;
    }

    public static CustomConfig getData() {
        return data;
    }

    public static CustomConfig getMysqlSettings() {
        return mysqlSettings;
    }

    public static boolean getDebug() {
        return instance.getConfig().getBoolean("debug");
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        async.set(getConfig().getBoolean("async"));
        Bot.start();
        registerEvent(new OnCommand());
        registerEvent(new NewPlayer());
        registerEvent(new OnBind());
        data = new CustomConfig("data.yml", this);
        mysqlSettings = new CustomConfig("mysql.yml", this);
        if (mysqlSettings.getConfig().getBoolean("enable")) {
            MySQL.setUP();
        }
        getLogger().info("Loaded");
    }

    private void registerEvent(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        if (MySQL.ENABLED) {
            MySQL.close();
            return;
        }
        data.save();
        Bot.stop();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        reloadConfig();
        async.set(getConfig().getBoolean("async"));
        data.reload();
        Bot.stop();
        Bot.start();
        sender.sendMessage("§a所有配置文件已经重新载入!");
        return true;
    }
}
