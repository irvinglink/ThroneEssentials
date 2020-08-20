package com.github.irvinglink.ThroneEssentials.Commands.CISubCommands;

import com.github.irvinglink.ThroneEssentials.Commands.SubCommand;
import com.github.irvinglink.ThroneEssentials.CommonUse;
import com.github.irvinglink.ThroneEssentials.Handlers.Chat;
import com.github.irvinglink.ThroneEssentials.Handlers.CustomItem;
import com.github.irvinglink.ThroneEssentials.Handlers.User;
import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CIGive extends SubCommand {

    private final MClass plugin;
    private final Chat chat;
    private final CommonUse commonUse;

    private final FileConfiguration lang;

    public CIGive(MClass plugin) {
        this.plugin = plugin;
        this.chat = plugin.getChat();

        this.lang = plugin.getLang();
        this.commonUse = plugin.getCommonUse();
    }

    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getDescription() {
        return "Give a player a custom item.";
    }

    @Override
    public String getSyntax() {
        return "/Customitems give <player> <item>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 3 || args.length == 4) {

            String targetName = args[1];
            int amount = 1;

            if (args.length == 4) amount = Integer.parseInt(args[3]);

            Player target = Bukkit.getPlayer(commonUse.getOfflineUUID(targetName));
            assert target != null;

            User user = new User(plugin, player.getUniqueId());
            User targetUser = new User(plugin, target.getUniqueId());

            if (!target.isOnline())
                targetUser.sendMessage(target, lang.getString("No_Online"), true);

            String itemName = args[2];

            ItemStack itemToAdd = CustomItem.getItem(plugin, itemName).getItem();
            itemToAdd.setAmount(amount);

            target.getInventory().addItem(itemToAdd);

            user.sendMessage(target, lang.getString("CustomItems_Give_Success_Player"), true);
            targetUser.sendMessage(player, lang.getString("CustomItems_Give_Success_Target"), true);

        } else
            player.sendMessage(chat.replace(player, null, lang.getString("CustomItems_Give_Syntax"), true));

    }
}
