/*
 * StBukkitLib - Set of useful Bukkit-related classes
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://google.com/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.stealthyone.mcb.stbukkitlib.backend.autosaving;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.lib.autosaving.Autosavable;
import com.stealthyone.mcb.stbukkitlib.lib.utils.TimeUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class AutosaveBackend {

    private StBukkitLib plugin;

    private Map<String, Map<String, AutosaveItem>> autosaveItems = new HashMap<String, Map<String, AutosaveItem>>();

    public AutosaveBackend(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    public void registerAutosavable(JavaPlugin plugin, String name, final Autosavable autosavable, int seconds) {
        Map<String, AutosaveItem> autosavables = getAutosaveItems(plugin.getName());
        if (autosavables == null) {
            autosavables = new HashMap<String, AutosaveItem>();
        }
        AutosaveItem newItem = new AutosaveItem(plugin, name, autosavable, seconds);
        autosavables.put(name.toLowerCase(), newItem);
        Log.debug("Registered autosavable: " + name + " from plugin: " + plugin.getClass().getName());
        plugin.getLogger().log(Level.INFO, String.format("Autosaving started, set to run every %s.", TimeUtils.translateSeconds(seconds)));
    }

    public Map<String, AutosaveItem> getAutosaveItems(String pluginName) {
        return autosaveItems.get(pluginName.toLowerCase());
    }

}