/*
 * StBukkitLib - AutosaveManager
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.backend.autosaving;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.lib.autosaving.Autosavable;
import com.stealthyone.mcb.stbukkitlib.lib.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class AutosaveManager {

    private StBukkitLib plugin;

    private Map<String, Map<String, AutosaveItem>> autosaveItems = new HashMap<String, Map<String, AutosaveItem>>();

    public AutosaveManager(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    public final void registerAutosavable(JavaPlugin plugin, String name, final Autosavable autosavable, int seconds) {
        Map<String, AutosaveItem> autosavables = getAutosaveItems(plugin.getName());
        if (autosavables == null) {
            autosavables = new HashMap<String, AutosaveItem>();
        }
        AutosaveItem newItem = new AutosaveItem(plugin.getName(), name, autosavable, seconds);
        autosavables.put(name.toLowerCase(), newItem);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public final void run() {
                autosavable.saveAll();
            }
        }, seconds * 20, seconds * 20);
        Log.debug("Registered autosavable: " + name + " from plugin: " + plugin.getClass().getName());
        plugin.getLogger().log(Level.INFO, String.format("Autosaving started, set to run every %s.", TimeUtils.translateSeconds(seconds)));
    }

    public final Map<String, AutosaveItem> getAutosaveItems(String pluginName) {
        return autosaveItems.get(pluginName.toLowerCase());
    }

}