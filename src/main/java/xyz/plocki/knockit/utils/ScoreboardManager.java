package xyz.plocki.knockit.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import xyz.plocki.knockit.KnockIT;

import java.util.HashMap;
import java.util.Objects;

public class ScoreboardManager {

    @SuppressWarnings("deprecation")
    public static void update() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(KnockIT.getPlugin(), () -> {
            for(Scoreboard board : boards.keySet()) {
                if(boards.get(board).isOnline()) {
                    Player p = boards.get(board);
                    Objects.requireNonNull(board.getTeam("coins")).setSuffix(""+CoinsAPI.getCoins(p));
                    Objects.requireNonNull(board.getTeam("kills")).setSuffix(""+StatsAPI.getKills(p));
                    Objects.requireNonNull(board.getTeam("deaths")).setSuffix(""+StatsAPI.getDeaths(p));
                }
            }
        }, 0, 10);
    }

    private static final HashMap<Scoreboard, Player> boards = new HashMap<>();

    public static void sendScoreboard(Player p){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("aaa", "bbb");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§8» §e§lKnock§6§lIT §8«");
        obj.getScore("§f").setScore(9);
        obj.getScore("§7Coins").setScore(8);
        obj.getScore("§a ").setScore(6);
        obj.getScore("§7Kills").setScore(5);
        obj.getScore("§e ").setScore(3);
        obj.getScore("§7Tode").setScore(2);
        obj.getScore("§1").setScore(0);

        Team coins = board.registerNewTeam("coins");
        coins.setPrefix("§6» ");
        coins.setSuffix(""+CoinsAPI.getCoins(p));
        coins.addEntry(ChatColor.RED.toString());

        Team kills = board.registerNewTeam("kills");
        kills.setPrefix("§a» ");
        kills.setSuffix(""+StatsAPI.getKills(p));
        kills.addEntry(ChatColor.AQUA.toString());

        Team deaths = board.registerNewTeam("deaths");
        deaths.setPrefix("§c» ");
        deaths.setSuffix(""+StatsAPI.getDeaths(p));
        deaths.addEntry(ChatColor.YELLOW.toString());

        obj.getScore(ChatColor.RED.toString()).setScore(7);
        obj.getScore(ChatColor.AQUA.toString()).setScore(4);
        obj.getScore(ChatColor.YELLOW.toString()).setScore(1);

        boards.put(board, p);
        p.setScoreboard(board);
    }

}
