package com.github.irvinglink.ThroneEssentials.Commands.CISubCommands;

import com.github.irvinglink.ThroneEssentials.Commands.SubCommand;
import com.github.irvinglink.ThroneEssentials.Handlers.Chat;
import com.github.irvinglink.ThroneEssentials.Handlers.CustomItem;
import com.github.irvinglink.ThroneEssentials.Handlers.User;
import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CIGiveAll extends SubCommand {

    private final MClass plugin;
    private final Chat chat;

    private final FileConfiguration lang;

    public CIGiveAll(MClass plugin) {
        this.plugin = plugin;
        this.chat = plugin.getChat();

        this.lang = plugin.getLang();
    }

    @Override
    public String getName() {
        return "giveall";
    }

    @Override
    public String getDescription() {
        return "Give to all the players a custom item";
    }

    @Override
    public String getSyntax() {
        return "/Customitems giveall <item>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 2 || args.length == 3) {

            String itemName = args[1];
            int amount = 1;

            if (args.length == 3) amount = Integer.parseInt(args[2]);

            User user = new User(plugin, player.getUniqueId());
            CustomItem customItem = CustomItem.getItem(plugin, itemName);

            for (Player target : Bukkit.getOnlinePlayers()) {

                User targetUser = new User(plugin, target.getUniqueId());

                ItemStack itemToAdd = customItem.getItem();
                itemToAdd.setAmount(amount);

                target.getInventory().addItem(itemToAdd);

                targetUser.sendMessage(player, lang.getString("CustomItems_Giveall_Success_Target"), true);

            }

            user.sendMessage(null, lang.getString("CustomItems_Giveall_Success_Player"), true);

        } else
            player.sendMessage(chat.replace(player, null, lang.getString("CustomItems_Giveall_Syntax"), true));

    }
}
