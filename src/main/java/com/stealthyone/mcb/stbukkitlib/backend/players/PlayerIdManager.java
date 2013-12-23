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
package com.stealthyone.mcb.stbukkitlib.backend.players;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.backend.exceptions.PlayerIdNotExistsException;
import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerIdManager {

    private StBukkitLib plugin;

    private YamlFileManager playerIds;
    private Map<String, String> nameCache = new HashMap<>(); //uuid, name
    private Map<String, String> idCache = new HashMap<>(); //name, uuid

    public PlayerIdManager(StBukkitLib plugin) {
        this.plugin = plugin;
        playerIds = new YamlFileManager(plugin.getDataFolder() + File.separator + "playerUUIDs.yml");
    }

    public void saveFile() {
        playerIds.saveFile();
    }

    public void updatePlayer(Player player) {
        playerIds.getConfig().set(player.getUniqueId().toString(), player.getName());
    }

    public String getName(UUID uuid) {
        if (nameCache.get(uuid.toString()) != null) {
            return nameCache.get(uuid.toString());
        }

        String name = playerIds.getConfig().getString(uuid.toString());
        if (name != null) {
            nameCache.put(uuid.toString(), name);
            return name;
        }
        return null;
    }

    public UUID getUuid(String playerName) {
        if (idCache.get(playerName.toLowerCase()) != null) {
            return UUID.fromString(idCache.get(playerName.toLowerCase()));
        }

        FileConfiguration config = playerIds.getConfig();
        for (String key : config.getKeys(false)) {
            if (config.getString(key).equalsIgnoreCase(playerName)) {
                UUID uuid = UUID.fromString(key);
                idCache.put(playerName.toLowerCase(), uuid.toString());
                return uuid;
            }
        }
        throw new PlayerIdNotExistsException(playerName);
    }

}