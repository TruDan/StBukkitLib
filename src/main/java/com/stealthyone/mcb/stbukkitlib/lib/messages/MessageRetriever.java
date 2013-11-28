package com.stealthyone.mcb.stbukkitlib.lib.messages;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.config.ConfigHelper;
import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import com.stealthyone.mcb.stbukkitlib.lib.utils.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

@Deprecated
public final class MessageRetriever {

    private JavaPlugin plugin;
    private YamlFileManager messageFile;

    public MessageRetriever(JavaPlugin plugin) {
        this(plugin, "messages.yml");
    }

    public MessageRetriever(JavaPlugin plugin, String fileName) {
        Log.debug("MessageRetriever, plugin is null: " + (plugin == null));
        this.plugin = plugin;
        messageFile = new YamlFileManager(new File(plugin.getDataFolder() + File.separator + fileName));
        if (!messageFile.getFile().exists() || messageFile.isEmpty()) {
            FileUtils.copyGenericFileFromJar(plugin, fileName);
            messageFile.reloadConfig();
        } else {
            messageFile.copyDefaults(YamlConfiguration.loadConfiguration(plugin.getResource(fileName)));
        }
    }

    public final void reloadMessages() {
        messageFile.reloadConfig();
    }

    public final String getTag() {
        return getTag(false);
    }

    public final String getTag(boolean raw) {
        String tag = messageFile.getConfig().getString("TAG", "&6[{PLUGINNAME}] ");
        return raw ? tag : ChatColor.translateAlternateColorCodes('&', tag).replace("{PLUGINNAME}", plugin.getName());
    }

    public final String[] getMessage(IMessagePath message) {
        String[] returnString;
        String path = message.getPrefix() + message.getMessagePath();

        if (message.isList()) {
            List<String> stringList = messageFile.getConfig().getStringList(path);
            returnString = (stringList == null) ? null : (String[]) stringList.toArray();
        } else {
            String string = messageFile.getConfig().getString(path);
            returnString = (string == null) ? null : new String[]{string};
        }
        String className = message.toString().toLowerCase() + (ConfigHelper.DEBUG.getBoolean() ? " (" + message.getClass().getName() + ")" : "");
        return returnString == null ? new String[]{className} : returnString;
    }
}