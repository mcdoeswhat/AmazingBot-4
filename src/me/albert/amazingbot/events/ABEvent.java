package me.albert.amazingbot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.concurrent.ConcurrentHashMap;

public class ABEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final transient ConcurrentHashMap<String, Object> metas = new ConcurrentHashMap<>();

    protected long time;

    protected long self_id;

    protected String post_type;

    public ABEvent() {
        super(true);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ConcurrentHashMap<String, Object> getMetas() {
        return metas;
    }

    public void addMeta(String key, Object object) {
        metas.put(key, object);
    }

    public Object getMeta(String key) {
        if (!hasMeta(key)) return null;
        return metas.get(key);
    }

    public boolean hasMeta(String key) {
        return metas.containsKey(key);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public String getPostType() {
        return post_type;
    }

    public long getSelfID() {
        return self_id;
    }

    public long getTime() {
        return time;
    }

}
