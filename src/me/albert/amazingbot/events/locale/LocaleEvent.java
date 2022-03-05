package me.albert.amazingbot.events.locale;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LocaleEvent extends Event {


    private static final HandlerList handlers = new HandlerList();

    public LocaleEvent() {
        super(true);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


}
