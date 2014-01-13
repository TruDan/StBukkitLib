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
package com.stealthyone.mcb.stbukkitlib.lib.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class JarYamlFileManager {

    private JavaPlugin plugin;
    private String fileName;
    private FileConfiguration config;

    public JarYamlFileManager(JavaPlugin plugin, String fileName) throws FileNotFoundException {
        this.plugin = plugin;
        this.fileName = fileName;
        InputStream stream = plugin.getResource(fileName);
        if (stream == null) {
            throw new FileNotFoundException("File '" + fileName + "' not found in jar archive for plugin: " + plugin.getName());
        } else {
            config = YamlConfiguration.loadConfiguration(stream);
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

}