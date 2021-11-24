package xyz.plocki.knockit.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.plocki.knockit.KnockIT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsAPI {
    
    private static final MySQLService service = KnockIT.getMySQL();

    public static void addDeaths(OfflinePlayer p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, getDeaths(p) + amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void resetDeaths(OfflinePlayer p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void setDeaths(OfflinePlayer p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void removeDeaths(OfflinePlayer p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, getDeaths(p) - amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static int getDeaths(OfflinePlayer p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerDeaths FROM stats WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if (rs.next())
                return rs.getInt("playerDeaths");
            setupPlayer(p);
            return getDeaths(p);
        } catch (SQLException sQLException) {
            return -1;
        }
    }

    public static void validateJoin(OfflinePlayer p) {
        if (getDeaths(p) == -1)
            setupPlayer(p);
    }

    public static void setupPlayer(OfflinePlayer p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO stats(playerDeaths,playerKills,uuid) VALUES (?,?,?)");
            ps.setInt(1, 0);
            ps.setInt(2, 0);
            ps.setString(3, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (Exception exception) {}
    }

    public static void addDeaths(Player p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, getDeaths(p) + amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void resetDeaths(Player p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void setDeaths(Player p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void removeDeaths(Player p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerDeaths = ? WHERE uuid = ?");
            ps.setInt(1, getDeaths(p) - amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static int getDeaths(Player p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerDeaths FROM stats WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if (rs.next())
                return rs.getInt("playerDeaths");
            setupPlayer(p);
            return getDeaths(p);
        } catch (SQLException sQLException) {
            return -1;
        }
    }

    public static void addKills(OfflinePlayer p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, getKills(p) + amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void resetKills(OfflinePlayer p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void setKills(OfflinePlayer p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void removeKills(OfflinePlayer p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, getKills(p) - amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static int getKills(OfflinePlayer p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerKills FROM stats WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if (rs.next())
                return rs.getInt("playerKills");
            setupPlayer(p);
            return getKills(p);
        } catch (SQLException sQLException) {
            return -1;
        }
    }

    public static void addKills(Player p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, getKills(p) + amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void resetKills(Player p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, 0);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void setKills(Player p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static void removeKills(Player p, int amount) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("UPDATE stats SET playerKills = ? WHERE uuid = ?");
            ps.setInt(1, getKills(p) - amount);
            ps.setString(2, p.getUniqueId().toString());
            service.executeUpdate(ps);
        } catch (SQLException sQLException) {}
    }

    public static int getKills(Player p) {
        try {
            PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerKills FROM stats WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = service.getResult(ps);
            if (rs.next())
                return rs.getInt("playerKills");
            setupPlayer(p);
            return getKills(p);
        } catch (SQLException sQLException) {
            return -1;
        }
    }
    
}
