package me.albert.amazingbot.utils.command;

import me.albert.amazingbot.AmazingBot;
import me.albert.amazingbot.events.message.MessageReceiveEvent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public class ConsoleSenderLegacy implements ConsoleCommandSender {
    private final MessageReceiveEvent event;
    private final ArrayList<String> output = new ArrayList<>();
    private final ArrayList<String> tempOutPut = new ArrayList<>();
    private BukkitTask task = null;

    public ConsoleSenderLegacy(MessageReceiveEvent event) {
        this.event = event;
    }

    private Optional<ConsoleCommandSender> get() {
        return Optional.of(Bukkit.getServer().getConsoleSender());
    }


    @Override
    public @NotNull Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public @NotNull String getName() {
        return "CONSOLE";
    }

    @NotNull
    @Override
    public Spigot spigot() {
        return null;
    }

    @Override
    public void sendMessage(@NotNull String message) {
        if (task != null) {
            task.cancel();
        }
        synchronized (tempOutPut) {
            tempOutPut.add(message);
        }
        task = Bukkit.getScheduler().runTaskLaterAsynchronously(AmazingBot.getInstance(), () -> {
            synchronized (output) {
                synchronized (tempOutPut) {
                    output.addAll(tempOutPut);
                    tempOutPut.clear();
                }
                StringBuilder response = new StringBuilder();
                for (String s : output) {
                    response.append(s.replaceAll("§\\S", "")).append("\n");
                }
                String msg = response.toString().trim();
                if (!msg.isEmpty()) {
                    event.response(msg);
                    output.clear();
                }
            }
        }, 4L);
    }


    @Override
    public void sendMessage(String[] messages) {
        for (String msg : messages) {
            sendMessage(msg);
        }
    }

    @Override
    public boolean isPermissionSet(@NotNull String s) {
        return get().map(c -> c.isPermissionSet(s)).orElse(true);
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission permission) {
        return get().map(c -> c.isPermissionSet(permission)).orElse(true);
    }

    @Override
    public boolean hasPermission(@NotNull String s) {
        return get().map(c -> c.hasPermission(s)).orElse(true);
    }

    @Override
    public boolean hasPermission(@NotNull Permission permission) {
        return get().map(c -> c.hasPermission(permission)).orElse(true);
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isConversing() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void acceptConversationInput(@NotNull String s) {
    }

    @Override
    public boolean beginConversation(@NotNull Conversation conversation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent conversationAbandonedEvent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendRawMessage(@NotNull String s) {
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b, int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment permissionAttachment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void recalculatePermissions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        throw new UnsupportedOperationException();
    }
}
