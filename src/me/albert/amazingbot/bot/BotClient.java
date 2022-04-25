package me.albert.amazingbot.bot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.albert.amazingbot.AmazingBot;
import me.albert.amazingbot.events.ABEvent;
import me.albert.amazingbot.events.locale.WebSocketConnectedEvent;
import me.albert.amazingbot.events.locale.WebSocketPostSendEvent;
import me.albert.amazingbot.events.locale.WebSocketPreSendEvent;
import me.albert.amazingbot.events.locale.WebSocketReceiveEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class BotClient extends WebSocketClient {

    private static final AtomicInteger recTask = new AtomicInteger(0);
    private static final AtomicInteger sendTask = new AtomicInteger(0);
    private static AmazingBot instance;
    private final ConcurrentHashMap<UUID, CompletableFuture<JsonObject>> responseMap = new ConcurrentHashMap<>();
    public int taskID;


    private BotClient(URI serverURI) {
        super(serverURI);
    }

    public BotClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
        instance = AmazingBot.getInstance();
    }

    private static void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void main(String[] args) throws URISyntaxException {
        BotClient c = new BotClient(new URI("ws://localhost:8887")); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }

    public JsonObject send(JsonObject object, int timeout) {
        sendTask.incrementAndGet();
        try {
            return processMessageSend(object, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendTask.decrementAndGet();
        int sendTip = instance.getConfig().getInt("main.send_task_tip");
        if (sendTask.get() > sendTip) {
            instance.getLogger().warning("检测到发送信息最大任务数已超过" + sendTip + ",可能有插件处理发送事件过于缓慢...");
        }
        return null;
    }

    public JsonObject processMessageSend(JsonObject object, int timeout) {
        //调用事件
        WebSocketPreSendEvent webSocketPreSendEvent = new WebSocketPreSendEvent(object);
        callEvent(webSocketPreSendEvent);
        object = webSocketPreSendEvent.getData();
        //调用事件完成
        UUID uuid = UUID.randomUUID();
        object.addProperty("echo", uuid.toString());
        CompletableFuture<JsonObject> response = new CompletableFuture<>();
        responseMap.put(uuid, response);
        String msg = object.toString();
        this.send(msg);
        if (AmazingBot.getDebug()) {
            int debugLength = instance.getConfig().getInt("debug_message_max_length");
            if (msg.length() > debugLength) {
                msg = msg.substring(0, debugLength) + "......(省略" + msg.length() + "字符)";
            }
            instance.getLogger().info("§a(DEBUG): 发送信息: " + msg);
        }
        //调用事件
        WebSocketPostSendEvent webSocketPostSendEvent = new WebSocketPostSendEvent(object);
        callEvent(webSocketPostSendEvent);
        //调用事件完成
        try {
            return response.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            responseMap.remove(uuid);
        }
        return null;
    }

    @Override
    public void onMessage(String msg) {
        if (!instance.isEnabled() || Bot.getClient() != this) {
            this.close();
            return;
        }
        recTask.incrementAndGet();
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            try {
                processMessageRec(msg);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                recTask.decrementAndGet();
            }
        });
        int recTip = instance.getConfig().getInt("main.rec_task_tip");
        if (recTask.get() > recTip) {
            instance.getLogger().warning("检测到接收信息最大任务数已超过" + recTip + ",可能有插件处理事件过于缓慢...");
        }
    }

    public void processMessageRec(String msg) {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(msg, JsonObject.class);
        if (AmazingBot.getDebug()) {
            instance.getLogger().info("§a(DEBUG): 收到信息: " + object);
        }
        WebSocketReceiveEvent webSocketReceiveEvent = new WebSocketReceiveEvent(object);
        callEvent(webSocketReceiveEvent);
        object = webSocketReceiveEvent.getData();
        if (object.has("post_type")) {
            ABEvent abEvent = new BotEventParser(object).parseEvent();
            callEvent(abEvent);
        }
        if (object.has("echo")) {
            UUID uuid = UUID.fromString(object.get("echo").getAsString());
            CompletableFuture<JsonObject> response = responseMap.get(uuid);
            if (response != null) {
                response.complete(object);
            }
        }
    }


    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if (Bot.getClient() != this) {
            this.close();
        }
        AmazingBot.getInstance().getLogger().info("§a机器人连接成功!");
        callEvent(new WebSocketConnectedEvent());
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        AmazingBot.getInstance().getLogger().warning("机器人连接关闭: " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
        int delay = instance.getConfig().getInt("main.auto_reconnect");
        if (instance.isEnabled() && code != 1000) {
            AmazingBot.getInstance().getLogger().info("§a将在" + delay + "秒后再次尝试连接");
            taskID = Bukkit.getScheduler().runTaskLater(instance, () -> {
                if (Bot.getClient() != this) {
                    return;
                }
                reconnect();
            }, 20L * delay).getTaskId();
        }
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

}