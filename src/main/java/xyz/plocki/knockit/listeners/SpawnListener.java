package xyz.plocki.knockit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SpawnListener implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

}
