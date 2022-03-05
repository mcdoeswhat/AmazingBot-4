package me.albert.amazingbot.objects.info.status;

public class BotStatistic {
    protected long PacketReceived;
    protected long PacketSent;
    protected long PacketLost;
    protected long MessageReceived;
    protected long MessageSent;
    protected int DisconnectTimes;
    protected int LostTimes;
    protected long LastMessageTime;

    public long getPacketReceived() {
        return PacketReceived;
    }

    public long getPacketSent() {
        return PacketSent;
    }

    public long getPacketLost() {
        return PacketLost;
    }

    public long getMessageReceived() {
        return MessageReceived;
    }

    public long getMessageSent() {
        return MessageSent;
    }

    public int getDisconnectTimes() {
        return DisconnectTimes;
    }

    public int getLostTimes() {
        return LostTimes;
    }

    public long getLastMessageTime() {
        return LastMessageTime;
    }
}
