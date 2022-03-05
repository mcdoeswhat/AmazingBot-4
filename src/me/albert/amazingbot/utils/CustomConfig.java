package me.albert.amazingbot.utils;

import com.google.common.base.Charsets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class CustomConfig {
    private final String filename;
    private final Plugin plugin;
    private FileConfiguration config;
    private File configFile;

    public CustomConfig(String name, Plugin plugin) {
        this.plugin = plugin;
        this.filename = name;
        this.create(name);
    }

    public String getFilename() {
        return this.filename;
    }

    public void save() {
        try {
            this.config.save(this.configFile);
        } catch (IOException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, ex);
        }
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defConfigStream = this.plugin.getResource(configFile.getName());
        if (defConfigStream != null) {
            this.config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void create(String file) {
        File ConfigFile = new File(this.plugin.getDataFolder(), file);
        if (!ConfigFile.exists()) {
            ConfigFile.getParentFile().mkdirs();
            if (this.plugin.getResource(file) != null) {
                this.plugin.saveResource(file, false);
            } else {
                try {
                    ConfigFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.configFile = ConfigFile;
        reload();
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public File getConfigFile() {
        return this.configFile;
    }
}