package com.stealthyone.mcb.stbukkitlib.backend.help;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.lib.help.HelpManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public final class HelpBackend {

    private StBukkitLib plugin;

    private Map<String, HelpManager> helpObjects = new HashMap<String, HelpManager>();

    public HelpBackend(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    public final boolean registerHelpObject(JavaPlugin plugin) {
        if (getHelpManager(plugin) != null) {
            return false;
        } else {
            HelpManager newObject;
            try {
                newObject = new HelpManager(plugin);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                return false;
            }
            String pluginName = plugin.getName();
            helpObjects.put(pluginName.toLowerCase(), newObject);
            Log.info("Registered help manager for plugin: '" + pluginName + "'");
            return true;
        }
    }

    public final HelpManager getHelpManager(String pluginName) {
        return helpObjects.get(pluginName.toLowerCase());
    }

    public final HelpManager getHelpManager(JavaPlugin plugin) {
        return getHelpManager(plugin.getName());
    }


}