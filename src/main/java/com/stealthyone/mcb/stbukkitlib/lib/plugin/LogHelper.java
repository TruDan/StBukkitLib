/*
 * StBukkitLib
 * Copyright (C) 2014 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/bukkit>
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