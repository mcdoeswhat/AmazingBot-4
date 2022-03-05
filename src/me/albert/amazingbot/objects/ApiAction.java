package me.albert.amazingbot.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.albert.amazingbot.bot.Bot;

public class ApiAction {

    private final JsonObject data = new JsonObject();

    private final JsonObject params = new JsonObject();

    private JsonObject result = null;

    public ApiAction(String action) {
        this.data.addProperty("action", action);
        data.add("params", params);
    }

    public ApiAction param(String key, JsonElement value) {
        params.add(key, value);
        data.add("params", params);
        return this;
    }

    public ApiAction param(String key, String value) {
        params.addProperty(key, value);
        data.add("params", params);
        return this;
    }

    public ApiAction param(String key, Number value) {
        params.addProperty(key, value);
        data.add("params", params);
        return this;
    }

    public ApiAction param(String key, boolean value) {
        params.addProperty(key, value);
        data.add("params", params);
        return this;
    }

    public boolean requestAndGetStatus() {
        result = Bot.getApi().sendRawData(getData());
        return result.get("status").getAsString().equals("ok");
    }

    public JsonElement requestAndGetData(){
        result = Bot.getApi().sendRawData(getData());
        if (!getStatus()) return null;
        return result.get("data");
    }

    public boolean getStatus() {
        if (result == null) {
            return false;
        }
        return result.get("status").getAsString().equals("ok");
    }


    public JsonObject request() {
        result = Bot.getApi().sendRawData(getData());
        return result;
    }

    public JsonObject getData() {
        return data;
    }
}
