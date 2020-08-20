package com.github.irvinglink.ThroneEssentials.Listeners;

import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class ProjectileHit implements Listener {

    private final MClass plugin;
    private final FileConfiguration config;

    public ProjectileHit(MClass plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onLaunch(ProjectileHitEvent event) {

        if (event.getEntity().getShooter() instanceof Player) {

            Player player = (Player) event.getEntity().getShooter();

            if (event.getEntity() instanceof Snowball) {

                Entity projectile = event.getEntity();

                Entity hitEntity = event.getHitEntity();

                if (hitEntity == null) return;

                Vector v1 = projectile.getVelocity();

                v1.multiply(config.getInt("Snowball_Multiplier"));

                hitEntity.setVelocity(v1);

                return;

            }

            return;
        }

    }
}
