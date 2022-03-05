package me.albert.amazingbot.events.locale;

import com.google.gson.JsonObject;

public class WebSocketPreSendEvent extends LocaleEvent {

    private JsonObject data;

    public WebSocketPreSendEvent(JsonObject data){
        this.data = data;
    }


    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
