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

import com.stealthyone.mcb.stbukkitlib.lib.autosaving.Autosavable;
import com.stealthyone.mcb.stbukkitlib.lib.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class AutosaveItem {

    private JavaPlugin plugin;
    private Autosavable autosavable;
    private int seconds;
    private int schedulerId;

    public AutosaveItem(JavaPlugin plugin, Autosavable autosavable) {
        this.plugin = plugin;
        this.autosavable = autosavable;
        this.seconds = plugin.getConfig().getInt("Autosave interval") * 60;
        if (seconds <= 0) {
            plugin.getLogger().log(Level.WARNING, "Autosaver disabled. It is recommended that you enable it to prevent data loss.");
        } else if (schedule()) {
            plugin.getLogger().log(Level.INFO, String.format("Autosaving started, set to run every %s.", TimeUtils.translateSeconds(seconds)));
        } else {
            plugin.getLogger().log(Level.WARNING, "Unable to start autosaving.");
        }
    }

    public boolean schedule() {
        schedulerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public final void run() {
                autosavable.saveAll();
            }
        }, seconds * 20, seconds * 20);
        return schedulerId != -1;
    }

    public void cancel() {
        if (schedulerId != -1)
            Bukkit.getScheduler().cancelTask(schedulerId);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Autosavable getAutosavable() {
        return autosavable;
    }

    public int getSeconds() {
        return seconds;
    }

}