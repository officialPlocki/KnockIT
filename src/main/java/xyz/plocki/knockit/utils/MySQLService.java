package xyz.plocki.knockit.utils;

import java.sql.*;

public class MySQLService {
  public static Connection con;
  
  private PreparedStatement ps;
  
  public static void connect(String host, String user, String database, String password, String port) {
    if (isConnected())
      return; 
    try {
      con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&autoReconnect=true");
    } catch (SQLException ignored) {}
  }
  
  public static void setMaxConnections() {
    try {
      PreparedStatement st = con.prepareStatement("SET GLOBAL MAX_CONNECTIONS = 500");
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static void disconnect() {
    if (isConnected())
      try {
        con.close();
      } catch (SQLException sQLException) {} 
  }
  
  public static boolean isConnected() {
    return (con != null);
  }
  
  public Connection getConnection() {
    return con;
  }
  
  public ResultSet getResult(PreparedStatement sql) {
    try {
      return sql.executeQuery();
    } catch (SQLException sQLException) {
      return null;
    } 
  }
  
  public void executeUpdate(PreparedStatement sql) {
    try {
      sql.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } 
  }

  public void executeUpdate(String statement) {
    try {
      con.prepareStatement(statement).executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
