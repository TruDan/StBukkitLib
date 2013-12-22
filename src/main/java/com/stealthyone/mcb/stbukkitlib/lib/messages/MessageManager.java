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
package com.stealthyone.mcb.stbukkitlib.lib.messages;

import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import com.stealthyone.mcb.stbukkitlib.lib.utils.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class MessageManager {

    private JavaPlugin plugin;
    private YamlFileManager messageFile;

    public MessageManager(JavaPlugin plugin) {
        this(plugin, "messages.yml");
    }

    public MessageManager(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        messageFile = new YamlFileManager(plugin.getDataFolder() + File.separator + fileName);
        if (messageFile.isEmpty()) {
            try {
                FileUtils.copyFileFromJar(plugin, fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            messageFile.reloadConfig();
        } else {
            messageFile.copyDefaults(YamlConfiguration.loadConfiguration(plugin.getResource(fileName)));
        }
    }

    public void reloadMessages() {
        messageFile.reloadConfig();
    }

    public String getTag() {
        return getTag(false);
    }

    public String getTag(boolean raw) {
        String tag = messageFile.getConfig().getString("TAG", "&6[{PLUGINNAME}] ");
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
        String message = messageFile.getConfig().getString(name);
        return message == null ? ChatColor.RED + "Undefined message '" + name + "'" : ChatColor.translateAlternateColorCodes('&', String.format(message, replacements)).replace("{TAG}", getTag());
    }

}