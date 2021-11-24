package xyz.plocki.knockit.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LocationUtil {

    private static final File file = new File("plugins/KnockIT/data.yml");
    private static final YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void setup() {
        if(!yml.isSet("spawn.world")) {
            yml.set("spawn.world", "world");
            yml.set("spawn.x", 0.0);
            yml.set("spawn.y", 0.0);
            yml.set("spawn.z", 0.0);
            yml.set("spawn.yaw", 0.0);
            yml.set("spawn.pitch", 0.0);
            yml.set("drop.height", 0.0);
            yml.set("drop.low", 0.0);
            try {
                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSpawn(Location location) {
        yml.set("spawn.world", location.getWorld().getName());
        yml.set("spawn.x", location.getX());
        yml.set("spawn.y", location.getY());
        yml.set("spawn.z", location.getZ());
        yml.set("spawn.yaw", (double) location.getYaw());
        yml.set("spawn.pitch", (double) location.getPitch());
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getSpawn() {
        return new Location(Bukkit.getWorld(Objects.requireNonNull(yml.getString("spawn.world"))), yml.getDouble("spawn.x"), yml.getDouble("spawn.y"), yml.getDouble("spawn.z"), Float.parseFloat(yml.getDouble("spawn.yaw")+""), Float.parseFloat(yml.getDouble("spawn.pitch")+""));
    }

    public void setHeight(double height) {
        yml.set("drop.height", height);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getHeight() {
        return yml.getDouble("drop.height");
    }

    public void setLow(double height) {
        yml.set("drop.low", height);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getLow() {
        return yml.getDouble("drop.low");
    }

}
