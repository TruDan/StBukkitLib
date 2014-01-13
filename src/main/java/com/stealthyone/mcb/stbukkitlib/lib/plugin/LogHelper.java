package com.stealthyone.mcb.stbukkitlib.lib.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class LogHelper {

    public static void DEBUG(JavaPlugin plugin, String msg) {
        if (plugin.getConfig().getBoolean("Debug")) {
            Bukkit.getLogger().log(Level.INFO, String.format("[%s DEBUG] %s", plugin.getName(), msg));
        }
    }

    public static void INFO(JavaPlugin plugin, String msg) {
        plugin.getLogger().info(msg);
    }

    public static void WARNING(JavaPlugin plugin, String msg) {
        plugin.getLogger().warning(msg);
    }

    public static void SEVERE(JavaPlugin plugin, String msg) {
        plugin.getLogger().severe(msg);
    }

}