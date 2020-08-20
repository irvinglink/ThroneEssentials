package com.github.irvinglink.ThroneEssentials.Commands.CISubCommands;

import com.github.irvinglink.ThroneEssentials.Commands.SubCommand;
import com.github.irvinglink.ThroneEssentials.Handlers.Chat;
import com.github.irvinglink.ThroneEssentials.Handlers.CustomItem;
import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CIList extends SubCommand {

    private final MClass plugin;
    private final Chat chat;

    private final FileConfiguration lang;

    public CIList(MClass plugin) {
        this.plugin = plugin;
        this.chat = plugin.getChat();
        this.lang = plugin.getLang();
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Displays you the custom items.";
    }

    @Override
    public String getSyntax() {
        return "/Customitems list";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 1) {

            player.sendMessage(chat.str("\n&aCustom Items &8List&b: "));

            CustomItem.getLoadedItems(plugin).forEach(x -> player.sendMessage(chat.str("&8[&a-&8] &a" + x.getItemName())));

        } else
            player.sendMessage(chat.replace(player, null, lang.getString("CustomItems_List_Syntax"), true));

    }
}
