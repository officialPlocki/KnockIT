package xyz.plocki.knockit.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        event.setCancelled(true);
    }

}
