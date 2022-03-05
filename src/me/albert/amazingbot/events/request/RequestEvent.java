package me.albert.amazingbot.events.request;

import me.albert.amazingbot.events.ABEvent;

public class RequestEvent extends ABEvent {
    protected String request_type;

    public String getRequestType() {
        return request_type;
    }
}
