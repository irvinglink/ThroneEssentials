package com.github.irvinglink.ThroneEssentials;

import com.github.irvinglink.ThroneEssentials.Commands.ClearAllCommand;
import com.github.irvinglink.ThroneEssentials.Commands.ClearCommand;
import com.github.irvinglink.ThroneEssentials.Commands.CustomItemsCommand;
import com.github.irvinglink.ThroneEssentials.Handlers.Chat;
import com.github.irvinglink.ThroneEssentials.Handlers.CustomItem;
import com.github.irvinglink.ThroneEssentials.Listeners.ProjectileHit;
import com.github.irvinglink.ThroneEssentials.Storage.FileSystem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MClass extends JavaPlugin {

    private Chat chat;
    private FileSystem fileSystem;
    private CommonUse commonUse;

    private FileConfiguration config;
    private FileConfiguration lang;

    @Override
    public void onEnable() {

        registerCommands();

        getServer().getPluginManager().registerEvents(new ProjectileHit(this), this);

        getServer().getConsoleSender().sendMessage(chat.str(Chat.EnumChat.PluginPrefix.getStr() + "The plugin has been enabled. &5Developed by&b irvinglink"));

    }

    @Override
    public void onLoad() {

        this.chat = new Chat(this);
        this.fileSystem = new FileSystem(this);
        this.commonUse = new CommonUse();

        fileSystem.setup();
        chat.registerHooks();

        CustomItem.getLoadedItems(this);

    }

    public void registerCommands() {
        new ClearCommand(this, "clear", "ThroneEssentials.Clear", true);
        new ClearAllCommand(this, "clearall", "ThroneEssentials.ClearAll", true);
        new CustomItemsCommand(this, "CustomItems", "ThroneEssentials.CustomItems", false);
    }

    @Override
    public void onDisable() {
        fileSystem.saveConfig();
    }

    public Chat getChat() {
        return chat;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public FileConfiguration getConfig() {
        return this.getFileSystem().getConfig();
    }

    public FileConfiguration getLang() {
        return this.getFileSystem().getLang();
    }

    public CommonUse getCommonUse() {
        return this.commonUse;
    }

}
