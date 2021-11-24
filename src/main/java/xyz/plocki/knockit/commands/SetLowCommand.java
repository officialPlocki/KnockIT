package xyz.plocki.knockit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.plocki.knockit.utils.LocationUtil;

import java.util.Objects;

public class SetLowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("knockit.setlow")) {
            new LocationUtil().setLow(Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getLocation().getY());
            sender.sendMessage("§e[KnockIT] §7Der Todes-höhe wurde gesetzt.");
        } else {
            sender.sendMessage("§e[KnockIT] §7Du hast dazu keine Rechte.");
        }
        return false;
    }

}
