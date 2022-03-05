package me.albert.amazingbot.objects.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ForwardMessage {

    protected JsonArray messages = new JsonArray();

    public ForwardMessage(JsonArray messages) {
        this.messages = messages;
    }

    public ForwardMessage() {

    }

    public JsonArray getMessages() {
        return messages;
    }

    public ForwardMessage add(Long uin, String name, String content) {
        JsonObject node = new JsonObject();
        node.addProperty("type", "node");
        JsonObject data = new JsonObject();
        data.addProperty("uin", uin);
        data.addProperty("name", name);
        data.addProperty("content", content);
        node.add("data", data);
        this.messages.add(node);
        return this;
    }
}
