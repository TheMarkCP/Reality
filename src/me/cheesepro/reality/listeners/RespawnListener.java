package me.cheesepro.reality.listeners;

import me.cheesepro.reality.Reality;
import me.cheesepro.reality.utils.RankGiver;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.*;

/**
 * Created by Mark on 2015-04-03.
 */
public class RespawnListener implements Listener{

    Reality plugin;
    Map<String, List<String>> settings;
    RankGiver rankGiver;

    public RespawnListener(Reality plugin){
        this.plugin = plugin;
        settings = plugin.getSettings();
        rankGiver = new RankGiver(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void respawnListener(PlayerRespawnEvent e){
        final Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Location loc = new Location(Bukkit.getWorld(settings.get("respawnlocation").get(0)),
                        Double.parseDouble(settings.get("respawnlocation").get(1)),
                        Double.parseDouble(settings.get("respawnlocation").get(2)),
                        Double.parseDouble(settings.get("respawnlocation").get(3)),
                        Float.parseFloat(settings.get("respawnlocation").get(4)),
                        Float.parseFloat(settings.get("respawnlocation").get(5)));
                p.teleport(loc);
            }
        }, 10);
        rankGiver.giveRank(p);
    }

}