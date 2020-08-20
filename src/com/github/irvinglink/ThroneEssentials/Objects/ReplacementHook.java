package com.github.irvinglink.ThroneEssentials.Objects;

import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.OfflinePlayer;

public class ReplacementHook {

    private final MClass plugin;

    public ReplacementHook(MClass plugin) {
        this.plugin = plugin;
    }

    public String replaceHook(OfflinePlayer player, OfflinePlayer target, String next) {

        assert player != null;
        assert target != null;

        switch (next) {

            case "target_name":
                return target.getName();

            case "player_name":
                return player.getName();

            case "prefix":
                return plugin.getLang().getString("Prefix");
        }
        return null;
    }
}
