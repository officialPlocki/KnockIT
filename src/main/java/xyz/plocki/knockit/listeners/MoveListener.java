package xyz.plocki.knockit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.plocki.knockit.utils.*;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(event.getTo().getBlock().getType().equals(Material.WATER) || event.getTo().getBlock().getType().equals(Material.LEGACY_STATIONARY_WATER) || event.getTo().getBlock().getType().equals(Material.LEGACY_WATER)) {
            event.getPlayer().teleport(new LocationUtil().getSpawn());
            new ItemUtil().giveItem(event.getPlayer());
            if(LastHit.hit.getOrDefault(event.getPlayer(), null) != null) {
                StatsAPI.addKills(LastHit.hit.get(event.getPlayer()), 1);
                CoinsAPI.addCoins(LastHit.hit.get(event.getPlayer()), 10);
                Bukkit.broadcastMessage("§e[KnockIT] §a" + LastHit.hit.get(event.getPlayer()).getName() + "§7 hat §6" + event.getPlayer().getName() + "§7 von der Plattform gestoßen.");
                LastHit.hit.remove(event.getPlayer());
            }
            StatsAPI.addDeaths(event.getPlayer(), 1);
            CoinsAPI.removeCoins(event.getPlayer(), 5);
            if(CoinsAPI.getCoins(event.getPlayer()) < 0 ) {
                CoinsAPI.setCoins(event.getPlayer(), 0);
            }
        } else if(event.getTo().getY() <= new LocationUtil().getLow()) {
            event.getPlayer().teleport(new LocationUtil().getSpawn());
            new ItemUtil().giveItem(event.getPlayer());
            if(LastHit.hit.getOrDefault(event.getPlayer(), null) != null) {
                StatsAPI.addKills(LastHit.hit.get(event.getPlayer()), 1);
                CoinsAPI.addCoins(LastHit.hit.get(event.getPlayer()), 10);
                Bukkit.broadcastMessage("§e[KnockIT] §a" + LastHit.hit.get(event.getPlayer()).getName() + "§7 hat §6" + event.getPlayer().getName() + "§7 von der Plattform gestoßen.");
                LastHit.hit.remove(event.getPlayer());
            } else {
                Bukkit.broadcastMessage("§e[KnockIT] §6" + event.getPlayer().getName() + "§7 ist von der Plattform gefallen.");
            }
            StatsAPI.addDeaths(event.getPlayer(), 1);
            CoinsAPI.removeCoins(event.getPlayer(), 5);
            if(CoinsAPI.getCoins(event.getPlayer()) < 0 ) {
                CoinsAPI.setCoins(event.getPlayer(), 0);
            }
        }
    }

}
