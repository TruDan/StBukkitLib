package com.stealthyone.mcb.stbukkitlib.lib.messages;

import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import com.stealthyone.mcb.stbukkitlib.lib.utils.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
            FileUtils.copyGenericFileFromJar(plugin, fileName);
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
        return messageFile.getConfig().getString("TAG", "&6[{PLUGINNAME}] ");
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