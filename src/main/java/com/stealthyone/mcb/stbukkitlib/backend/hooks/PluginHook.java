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
package com.stealthyone.mcb.stbukkitlib.backend.hooks;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.api.Stbl;
import com.stealthyone.mcb.stbukkitlib.backend.exceptions.UnloadedHookException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PluginHook {

    private String name;

    public PluginHook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void load() throws UnloadedHookException {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
        if (plugin == null) {
            throw new UnloadedHookException();
        } else if (plugin.isEnabled()) {
            Log.info("Hooked with plugin: " + plugin.getName() + " version " + plugin.getDescription().getVersion());
        } else {
            Log.warning("Unable to hook with plugin: '" + plugin.getName() + "' -> plugin is not enabled.");
        }
    }

    public JavaPlugin getPlugin() {
        return (JavaPlugin) Bukkit.getPluginManager().getPlugin(name);
    }

    public String getVersion() {
        return getPlugin().getDescription().getVersion();
    }

    public final boolean isActive() {
        return Stbl.hooks.isHookActive(this);
    }

}