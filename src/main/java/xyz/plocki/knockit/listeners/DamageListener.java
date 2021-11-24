package xyz.plocki.knockit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.plocki.knockit.utils.LastHit;
import xyz.plocki.knockit.utils.LocationUtil;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity().getLocation().getY() > new LocationUtil().getHeight()) {
            event.setCancelled(true);
            return;
        }
        event.setDamage(0);
        ((Player)event.getEntity()).setHealth(20);
        LastHit.hit.put((Player) event.getEntity(), (Player) event.getDamager());
    }

    @EventHandler
    public void onDamageEntity(EntityDamageEvent event) {
        if(!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            event.setCancelled(true);
        }
        event.setDamage(0);
        ((Player)event.getEntity()).setHealth(20);
    }

}
