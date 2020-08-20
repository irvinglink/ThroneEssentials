package com.github.irvinglink.ThroneEssentials.Handlers;

import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {

    private final MClass plugin;
    private final Chat chat;
    private final UUID uuid;

    public User(MClass plugin, UUID uuid) {
        this.plugin = plugin;
        this.uuid = uuid;
        chat = plugin.getChat();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void sendMessage(String text) {
        getPlayer().sendMessage(chat.str(text));
    }

    public void sendMessage(OfflinePlayer target, String text, boolean color) {
        getPlayer().sendMessage(chat.replace(getPlayer(), target, text, color));
    }

    public UUID getUuid() {
        return uuid;
    }
}
