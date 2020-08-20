package com.github.irvinglink.ThroneEssentials.Commands;

import com.github.irvinglink.ThroneEssentials.Handlers.Chat;
import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class ThroneCommand implements CommandExecutor {

    private final MClass plugin;
    private final Chat chat;
    private final String commandName;
    private final String permission;
    private final boolean console;

    public ThroneCommand(MClass plugin, String commandName, String permission, boolean console) {

        this.plugin = plugin;
        this.chat = plugin.getChat();
        this.commandName = commandName;
        this.permission = permission;
        this.console = console;

        Objects.requireNonNull(this.plugin.getServer().getPluginCommand(commandName)).setExecutor(this);

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!cmd.getLabel().equalsIgnoreCase(commandName)) return true;

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(chat.replace((Player) sender, null, plugin.getLang().getString("No_Permission"), true));
            return true;
        }

        if (!console && !(sender instanceof Player)) {
            sender.sendMessage(chat.str(Chat.EnumChat.PluginPrefix.getStr() + "&cOnly players can use this command!"));
            return true;
        }

        execute(sender, args);
        return true;

    }

    public abstract void execute(CommandSender sender, String[] args);

}
