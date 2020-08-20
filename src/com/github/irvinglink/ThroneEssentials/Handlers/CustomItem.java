package com.github.irvinglink.ThroneEssentials.Handlers;

import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomItem {

    private final String itemName;
    private final String id;
    private final String displayName;
    private final boolean glow;
    private final List<String> lore;

    public CustomItem(String itemName, String id, String displayName, boolean glow, List<String> lore) {
        this.itemName = itemName;
        this.id = id;
        this.displayName = displayName;
        this.glow = glow;
        this.lore = lore;
    }

    public ItemStack getItem() {

        ItemStack item = new ItemStack(Material.valueOf(id.toUpperCase()));
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));


        if (glow) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (lore != null) itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);

        return item;
    }

    public String getItemName() {
        return itemName;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isGlow() {
        return glow;
    }

    public List<String> getLore() {
        return lore;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomItem that = (CustomItem) o;

        return glow == that.glow &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(id, that.id) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(lore, that.lore);

    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, id, displayName, glow, lore);
    }

    public static List<CustomItem> getLoadedItems(MClass plugin) {

        List<CustomItem> itemList = new ArrayList<>();
        FileConfiguration config = plugin.getConfig();

        for (String item : config.getConfigurationSection("CustomItems").getKeys(false)) {

            String path = "CustomItems." + item;

            String itemName = item;
            String id = config.getString(path + ".Id");
            String displayName = config.getString(path + ".DisplayName");
            boolean glow = config.getBoolean(path + ".Glow");
            List<String> lore = config.getStringList(path + ".Lore");

            CustomItem customItem = new CustomItem(itemName, id, displayName, glow, lore);

            if (!itemList.contains(customItem)) itemList.add(customItem);

        }

        return itemList;
    }

    public static CustomItem getItem(MClass plugin, String itemName) {

        CustomItem customItem = null;

        for (CustomItem item : getLoadedItems(plugin)) {

            if (item.getItemName().equalsIgnoreCase(itemName)) customItem = item;
        }

        return customItem;
    }

}
