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
package com.stealthyone.mcb.stbukkitlib.lib.messages;

import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {

    protected JavaPlugin plugin;
    protected YamlFileManager messageFile;

    protected Map<String, Map<String, String>> messages = new HashMap<>();

    public MessageManager(JavaPlugin plugin) {
        this(plugin, "messages.yml");
    }

    public MessageManager(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        messageFile = new YamlFileManager(plugin.getDataFolder() + File.separator + fileName);
        if (messageFile.isEmpty()) {
            plugin.saveResource(fileName, true);
        } else {
            messageFile.copyDefaults(YamlConfiguration.loadConfiguration(plugin.getResource(fileName)));
        }
        reloadMessages();
    }

    public void reloadMessages() {
        messageFile.reloadConfig();
        messages.clear();

        FileConfiguration config = messageFile.getConfig();
        for (String key : config.getKeys(false)) {
            if (key.equals("tag") || !(config.get(key) instanceof List)) continue;

            Map<String, String> secMessages = new HashMap<>();
            for (String messageName : config.getConfigurationSection(key).getKeys(false)) {
                secMessages.put(messageName, config.getString(messageName));
            }
            messages.put(key, secMessages);
        }
    }

    public String getTag() {
        return getTag(false);
    }

    public String getTag(boolean raw) {
        String tag = messageFile.getConfig().getString("tag", "&6[{PLUGINNAME}] ");
        return raw ? tag : ChatColor.translateAlternateColorCodes('&', tag.replace("{PLUGINNAME}", plugin.getName()));
    }

    public String getMessage(MessageReferencer message) {
        return getMessage(message.getMessagePath());
    }

    public String getMessage(String name) {
        String message = messageFile.getConfig().getString(name);
        return message == null ? ChatColor.RED + "Undefined message '" + name + "'" : ChatColor.translateAlternateColorCodes('&', message).replace("{TAG}", getTag());
    }

    public String getMessage(MessageReferencer message, String... replacements) {
        return getMessage(message.getMessagePath(), replacements);
    }

    public String getMessage(String name, String... replacements) {
        String[] nameSplit = name.split(".");
        String message;
        try {
            message = messages.get(nameSplit[0]).get(nameSplit[1]);
        } catch (NullPointerException ex) {
            message = null;
        }
        return message == null ? ChatColor.RED + "Undefined message '" + name + "'" : ChatColor.translateAlternateColorCodes('&', String.format(message, replacements)).replace("{TAG}", getTag());
    }

}