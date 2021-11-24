package xyz.plocki.knockit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.plocki.knockit.utils.LocationUtil;

import java.util.Objects;

public class SetHeightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("knockit.setheight")) {
            new LocationUtil().setHeight(Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getLocation().getY());
            sender.sendMessage("§e[KnockIT] §7Der Kampf-höhe wurde gesetzt.");
        } else {
            sender.sendMessage("§e[KnockIT] §7Du hast dazu keine Rechte.");
        }
        return false;
    }

}
