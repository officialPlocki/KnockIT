package xyz.plocki.knockit.listeners;

import com.google.common.util.concurrent.AtomicDouble;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.plocki.knockit.KnockIT;
import xyz.plocki.knockit.utils.LocationUtil;

public class BlockBuildListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if(event.getBlock().getLocation().getY() >= new LocationUtil().getHeight()) {
            event.setCancelled(true);
            return;
        }
        event.getPlayer().getItemInHand().setAmount(64);

        ArmorStand armorStand = (ArmorStand) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation().clone().add(0,1,0), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCollidable(false);

        AtomicDouble doub = new AtomicDouble(6);

        int scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(KnockIT.getPlugin(), () -> {
            if(doub.get() <= 0) {
                armorStand.remove();
            }
            if(doub.get() <= 3) {
                armorStand.setCustomName("§c"+doub.getAndSet(doub.get()-0.05));
            } else if(doub.get() <= 1) {
                armorStand.setCustomName("§4"+doub.getAndSet(doub.get()-0.05));
            } else {
                armorStand.setCustomName("§7"+doub.getAndSet(doub.get()-0.05));
            }
        }, 0, 1);

        Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIT.getPlugin(), () -> {
            Bukkit.getScheduler().cancelTask(scheduler);
        }, 122);
        Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIT.getPlugin(), () -> {
            event.getBlock().getLocation().getBlock().setType(Material.REDSTONE_BLOCK);
        }, 20*3);
        Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIT.getPlugin(), () -> {
            event.getBlock().getLocation().getBlock().setType(Material.AIR);
        }, 20*6);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        event.setCancelled(true);
    }

}
