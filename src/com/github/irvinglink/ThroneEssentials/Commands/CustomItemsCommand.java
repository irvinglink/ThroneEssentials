package com.github.irvinglink.ThroneEssentials.Commands;

import com.github.irvinglink.ThroneEssentials.Commands.CISubCommands.CIGive;
import com.github.irvinglink.ThroneEssentials.Commands.CISubCommands.CIGiveAll;
import com.github.irvinglink.ThroneEssentials.Commands.CISubCommands.CIList;
import com.github.irvinglink.ThroneEssentials.Handlers.Chat;
import com.github.irvinglink.ThroneEssentials.Handlers.CustomItem;
import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CustomItemsCommand extends ThroneCommand implements TabCompleter {

    private final MClass plugin;
    private final Chat chat;
    private final FileConfiguration lang;

    private List<SubCommand> subCommands = new ArrayList<>();

    public CustomItemsCommand(MClass plugin, String commandName, String permission, boolean console) {
        super(plugin, commandName, permission, console);

        this.plugin = plugin;
        this.chat = plugin.getChat();
        this.lang = plugin.getLang();

        subCommands.add(new CIGive(plugin));
        subCommands.add(new CIGiveAll(plugin));
        subCommands.add(new CIList(plugin));

    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length == 0) {

            sender.sendMessage(chat.str(Chat.EnumChat.PluginPrefix.getStr() + "&eCustomItems Commands"));
            subCommands.forEach(x -> sender.sendMessage(chat.str("&7[&b-&7] &a" + x.getSyntax() + " &e"+x.getDescription())));

            return;

        }

        for (int i = 0; i < subCommands.size(); i++) {
            if (args[0].equalsIgnoreCase(subCommands.get(i).getName())) {
                subCommands.get(i).perform((Player) sender, args);
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {
            List<String> argsList = new ArrayList<>();

            subCommands.forEach(x -> argsList.add(x.getName()));

            return argsList;
        }

        if (args.length == 2) {

            List<String> playerList = new ArrayList<>();

            Bukkit.getOnlinePlayers().forEach(player -> playerList.add(player.getName()));

            return playerList;

        }

        if (args.length == 3) {

            List<String> itemList = new ArrayList<>();

            CustomItem.getLoadedItems(plugin).forEach(item -> itemList.add(item.getItemName()));

            return itemList;

        }

        return null;
    }
}
