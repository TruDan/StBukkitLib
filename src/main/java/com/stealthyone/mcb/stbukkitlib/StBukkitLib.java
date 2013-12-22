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
package com.stealthyone.mcb.stbukkitlib;

import com.stealthyone.mcb.stbukkitlib.api.Stbl;
import com.stealthyone.mcb.stbukkitlib.backend.autosaving.AutosaveBackend;
import com.stealthyone.mcb.stbukkitlib.backend.hooks.HookManager;
import com.stealthyone.mcb.stbukkitlib.backend.players.PlayerIdManager;
import com.stealthyone.mcb.stbukkitlib.config.ConfigHelper;
import com.stealthyone.mcb.stbukkitlib.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StBukkitLib extends JavaPlugin {

    public static class Log {

        public static void debug(String msg) {
            if (ConfigHelper.DEBUG.get())
                instance.logger.log(Level.INFO, String.format("[%s DEBUG] %s", instance.getName(), msg));
        }

        public static void info(String msg) {
            instance.logger.log(Level.INFO, String.format("[%s] %s", instance.getName(), msg));
        }

        public static void warning(String msg) {
            instance.logger.log(Level.WARNING, String.format("[%s] %s", instance.getName(), msg));
        }

        public static void severe(String msg) {
            instance.logger.log(Level.SEVERE, String.format("[%s] %s", instance.getName(), msg));
        }
    }

    private static StBukkitLib instance;
    {
        instance = this;
    }

    public static StBukkitLib getInstance() {
        return instance;
    }

    private Logger logger;

    @Override
    public void onLoad() {
        logger = Bukkit.getLogger();
        getDataFolder().mkdir();
    }

    @Override
    public void onEnable() {
        /* Setup config */
        saveDefaultConfig();
        getConfig().options().copyDefaults(false);
        saveConfig();

        /* Setup important plugin components */
        Stbl.autosaving = new AutosaveBackend(this);
        Stbl.hooks = new HookManager(this);
        Stbl.playerIds = new PlayerIdManager(this);

        //Stbl.autosaving.registerAutosavable(this, "main", this, );

        /* Register listeners */
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);

        Log.info(String.format("%s v%s by Stealth2800 enabled.", getName(), getDescription().getVersion()));
    }

    @Override
    public void onDisable() {
        saveAll();
        Log.info(String.format("%s v%s by Stealth2800 disabled.", getName(), getDescription().getVersion()));
    }

    public void saveAll() {
        Stbl.playerIds.saveFile();
    }

}