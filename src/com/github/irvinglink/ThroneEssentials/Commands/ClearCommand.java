package com.github.irvinglink.ThroneEssentials.Commands;

import com.github.irvinglink.ThroneEssentials.Handlers.Chat;
import com.github.irvinglink.ThroneEssentials.Handlers.User;
import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ClearCommand extends ThroneCommand {

    private final MClass plugin;
    private final Chat chat;
    private final FileConfiguration config;
    private final FileConfiguration lang;

    public ClearCommand(MClass plugin, String commandName, String permission, boolean console) {
        super(plugin, commandName, permission, console);
        this.plugin = plugin;
        this.chat = plugin.getChat();
        this.config = plugin.getConfig();
        this.lang = plugin.getLang();
    }

    @Override
    public void execute(CommandSender sender, final String[] args) {

        switch (args.length) {

            case 0:

                if (!(sender instanceof Player)) return;

                User player = new User(plugin, ((Player) sender).getUniqueId());
                for (int i = 0; i <= 35; i++) {
                    player.getPlayer().getInventory().clear(i);
                }

                player.getPlayer().getInventory().clear(40);

                player.sendMessage(chat.replace(player.getPlayer(), null, lang.getString("Self_Clean_Success"), true));
                break;

            case 1:

                if (sender instanceof Player) {
                    User x = new User(plugin, ((Player) sender).getUniqueId());
                    User target = new User(plugin, Objects.requireNonNull(Bukkit.getPlayer(args[0])).getUniqueId());

                    if (!target.getPlayer().isOnline()) {
                        Bukkit.getConsoleSender().sendMessage(chat.str(Chat.EnumChat.PluginPrefix.getStr() + "Jugador no encontrado"));
                        return;
                    }

                    for (int i = 0; i <= 35; i++) {
                        target.getPlayer().getInventory().clear(i);
                    }

                    target.getPlayer().getInventory().clear(40);

                    x.sendMessage(target.getPlayer(), lang.getString("Clean_Success"), true);
                    target.sendMessage(x.getPlayer(), lang.getString("Target_Clean_Success"), true);
                } else {

                    User target = new User(plugin, Objects.requireNonNull(Bukkit.getPlayer(args[0])).getUniqueId());

                    if (!target.getPlayer().isOnline()) {
                        Bukkit.getConsoleSender().sendMessage(chat.str(Chat.EnumChat.PluginPrefix.getStr() + "Jugador no encontrado"));
                        return;
                    }

                    for (int i = 0; i <= 35; i++) {
                        target.getPlayer().getInventory().clear(i);
                    }

                    Bukkit.getConsoleSender().sendMessage(chat.replace(null, target.getPlayer(), lang.getString("Clean_Success"), true));
                    target.sendMessage(null, lang.getString("Target_Clean_Success"), true);

                }
                break;
        }
    }
}
