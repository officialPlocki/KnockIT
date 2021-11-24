package xyz.plocki.knockit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.plocki.knockit.KnockIT;
import xyz.plocki.knockit.utils.*;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(null);
        CoinsAPI.validateJoin(event.getPlayer());
        StatsAPI.validateJoin(event.getPlayer());
        LastHit.hit.remove(event.getPlayer());
        ScoreboardManager.sendScoreboard(event.getPlayer());
        Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIT.getPlugin(), () -> {
            event.getPlayer().teleport(new LocationUtil().getSpawn());
            new ItemUtil().giveItem(event.getPlayer());
        }, 5);
    }

}
