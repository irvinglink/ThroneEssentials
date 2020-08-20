package com.github.irvinglink.ThroneEssentials.Handlers;

import com.github.irvinglink.ThroneEssentials.MClass;
import com.github.irvinglink.ThroneEssentials.Objects.ReplacementHook;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {

    private static Map<String, ReplacementHook> hookMap = new HashMap<>();
    private static Pattern pattern = Pattern.compile("[%]([^%]+)[%]");

    private final MClass plugin;

    public Chat(MClass plugin) {
        this.plugin = plugin;
    }

    public String str(String textToTranslate) {
        return ChatColor.translateAlternateColorCodes('&', textToTranslate);
    }

    public void registerHooks() {
        hookMap.put("throne", new ReplacementHook(plugin));
    }

    public String replace(OfflinePlayer player, OfflinePlayer target, String text, boolean color) {

        if (text == null) return null;

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

            String var = matcher.group(1);
            int index = var.indexOf("_");

            if (index <= 0 || index >= var.length()) continue;

            String identifier = var.substring(0, index);
            String next = var.substring(index + 1);

            if (hookMap.containsKey(identifier)) {
                String value = hookMap.get(identifier).replaceHook(player, target, next);

                if (value != null)
                    text = text.replaceAll(Pattern.quote(matcher.group()), Matcher.quoteReplacement(value));
            }

        }

        return (color) ? str(text) : text;
    }


    public enum EnumChat {

        PluginPrefix("&7[&eThroneEssentials&7] &f");

        private final String str;

        EnumChat(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }
}
