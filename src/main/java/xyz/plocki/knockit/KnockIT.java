package xyz.plocki.knockit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.plocki.knockit.commands.SetHeightCommand;
import xyz.plocki.knockit.commands.SetLowCommand;
import xyz.plocki.knockit.commands.SetSpawnCommand;
import xyz.plocki.knockit.listeners.*;
import xyz.plocki.knockit.utils.*;

import java.util.Objects;

public final class KnockIT extends JavaPlugin {

    private static Plugin plugin;
    private static MySQLService mySQLService;

    @Override
    public void onEnable() {
        plugin = this;
        FileBuilder fb = new FileBuilder("plugins/KnockIT/config.yml");
        YamlConfiguration conf = fb.getYaml();
        if (!fb.getFile().exists()) {
            conf.set("mysql.host", "localhost");
            conf.set("mysql.port", 3306);
            conf.set("mysql.user", "root");
            conf.set("mysql.password", "abc123");
            conf.set("mysql.database", "core");
            fb.save();
        }
        MySQLService.connect(conf.getString("mysql.host"), conf.getString("mysql.user"), conf.getString("mysql.database"), conf.getString("mysql.password"), conf.getString("mysql.port"));
        MySQLService.setMaxConnections();
        mySQLService = new MySQLService();
        mySQLService.executeUpdate("CREATE TABLE IF NOT EXISTS coins(playerCoins INT,uuid TEXT)");
        mySQLService.executeUpdate("CREATE TABLE IF NOT EXISTS stats(playerDeaths INT,playerKills INT,uuid TEXT)");
        LocationUtil.setup();
        ScoreboardManager.update();
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new DamageListener(), this);
        pluginManager.registerEvents(new HungerListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new BlockBuildListener(), this);
        pluginManager.registerEvents(new DropListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new SpawnListener(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        Objects.requireNonNull(this.getCommand("setheight")).setExecutor(new SetHeightCommand());
        Objects.requireNonNull(this.getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        Objects.requireNonNull(this.getCommand("setlow")).setExecutor(new SetLowCommand());
    }

    public static MySQLService getMySQL() {
        return mySQLService;
    }
    public static Plugin getPlugin() {
        return plugin;
    }
}
