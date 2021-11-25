package xyz.plocki.knockit.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

    public void giveItem(Player player) {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLocalizedName("§7» §6Knockback Stick §7«");
        itemMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);

        ItemStack blocks = new ItemStack(Material.GREEN_TERRACOTTA, 64);
        ItemMeta blocksMeta = blocks.getItemMeta();
        blocksMeta.setLocalizedName("§7» §eBaublöcke §7«");
        blocks.setItemMeta(blocksMeta);

        player.getInventory().setItem(0, item);
        player.getInventory().setItem(1, blocks);
    }

}
