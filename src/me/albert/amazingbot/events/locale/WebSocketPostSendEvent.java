package me.albert.amazingbot.events.locale;

import com.google.gson.JsonObject;

public class WebSocketPostSendEvent extends LocaleEvent{

    private final JsonObject data;

    public WebSocketPostSendEvent(JsonObject data){
        this.data = data;
    }


    public JsonObject getData() {
        return data;
    }
}
