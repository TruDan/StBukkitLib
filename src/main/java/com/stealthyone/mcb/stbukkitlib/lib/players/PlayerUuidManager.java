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
package com.stealthyone.mcb.stbukkitlib.lib.players;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.plugin.LogHelper;
import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PlayerUuidManager {

    private StBukkitLib plugin;

    private YamlFileManager idFile;
    private Map<String, String> namesToIds = new HashMap<>();
    private Map<String, String> idsToNames = new HashMap<>();

    public PlayerUuidManager(StBukkitLib plugin) {
        this.plugin = plugin;
        idFile = new YamlFileManager(plugin.getDataFolder() + File.separator + "playerIds.yml");
        LogHelper.INFO(plugin, "Loaded " + reloadData() + " ID to name mappings.");
    }

    public int reloadData() {
        namesToIds.clear();
        idsToNames.clear();
        idFile.reloadConfig();

        FileConfiguration idConfig = idFile.getConfig();
        for (String key : idConfig.getKeys(false)) {
            String name = idConfig.getString(key);
            if (name == null) {
                LogHelper.WARNING(plugin, "Unable to load NAME from ID: " + key);
                continue;
            }
            idsToNames.put(key, name.toLowerCase());
            idsToNames.put(name.toLowerCase(), key);
            LogHelper.DEBUG(plugin, "Mapped ID: " + key + " to NAME: " + name);
        }
        return idsToNames.size();
    }

    public String getName(String uuid) {
        return idsToNames.get(uuid);
    }

    public String getUuid(String name) {
        return namesToIds.get(name.toLowerCase());
    }

}