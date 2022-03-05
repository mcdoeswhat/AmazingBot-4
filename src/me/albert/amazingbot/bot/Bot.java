package me.albert.amazingbot.bot;

import me.albert.amazingbot.AmazingBot;
import org.bukkit.configuration.file.FileConfiguration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Bot {

    private static BotAPI api = new BotAPI(null);

    private static BotClient client;


    public static void start() {
        try {
            FileConfiguration config = AmazingBot.getInstance().getConfig();
            URI uri = new URI(config.getString("main.URI"));
            String token = config.getString("main.token");
            Map<String, String> httpHeaders = new HashMap<>();
            httpHeaders.put("Authorization", "Bearer " + token);
            client = new BotClient(uri, httpHeaders);
            client.connect();
            api = new BotAPI(client);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        client.close();
    }


    public static BotAPI getApi() {
        return api;
    }

    public static void setApi(BotAPI api) {
        Bot.api = api;
    }


    public static Boolean getConnected() {
        return !client.isClosed();
    }


}