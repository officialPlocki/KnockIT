package xyz.plocki.knockit.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.plocki.knockit.KnockIT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsAPI {

  private static final MySQLService service = KnockIT.getMySQL();

  public static void addCoins(OfflinePlayer p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, getCoins(p) + amount);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void resetCoins(OfflinePlayer p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, 0);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void setCoins(OfflinePlayer p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, amount);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void removeCoins(OfflinePlayer p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, getCoins(p) - amount);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException ignored) {}
  }
  
  public static int getCoins(OfflinePlayer p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerCoins FROM coins WHERE uuid = ?");
      ps.setString(1, p.getUniqueId().toString());
      ResultSet rs = service.getResult(ps);
      if (rs.next())
        return rs.getInt("playerCoins"); 
      setupPlayer(p);
      return getCoins(p);
    } catch (SQLException sQLException) {
      return -1;
    } 
  }
  
  public static void validateJoin(OfflinePlayer p) {
    if (getCoins(p) == -1)
      setupPlayer(p); 
  }
  
  public static void setupPlayer(OfflinePlayer p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO coins(playerCoins,uuid) VALUES (?,?)");
      ps.setInt(1, 0);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (Exception exception) {}
  }
  
  public static void addCoins(Player p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, getCoins(p) + amount);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void resetCoins(Player p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, 0);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void setCoins(Player p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, amount);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static void removeCoins(Player p, int amount) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("UPDATE coins SET playerCoins = ? WHERE uuid = ?");
      ps.setInt(1, getCoins(p) - amount);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (SQLException sQLException) {}
  }
  
  public static int getCoins(Player p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("SELECT playerCoins FROM coins WHERE uuid = ?");
      ps.setString(1, p.getUniqueId().toString());
      ResultSet rs = service.getResult(ps);
      if (rs.next())
        return rs.getInt("playerCoins"); 
      setupPlayer(p);
      return getCoins(p);
    } catch (SQLException sQLException) {
      return -1;
    } 
  }
  
  public static void validateJoin(Player p) {
    if (getCoins(p) == -1)
      setupPlayer(p); 
  }
  
  public static void setupPlayer(Player p) {
    try {
      PreparedStatement ps = service.getConnection().prepareStatement("INSERT INTO coins(playerCoins,uuid) VALUES (?,?)");
      ps.setInt(1, 0);
      ps.setString(2, p.getUniqueId().toString());
      service.executeUpdate(ps);
    } catch (Exception exception) {}
  }
}
