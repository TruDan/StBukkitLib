package com.stealthyone.mcb.stbukkitlib.lib.messages;

import java.io.File;
import java.util.List;

import com.stealthyone.mcb.stbukkitlib.config.ConfigHelper;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import com.stealthyone.mcb.stbukkitlib.lib.utils.FileUtils;

public final class MessageRetriever {

	private JavaPlugin plugin;
	private YamlFileManager messageFile;
	
	public MessageRetriever(JavaPlugin plugin) {
		this(plugin, "messages.yml");
	}
	
	public MessageRetriever(JavaPlugin plugin, String fileName) {
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
		String tag = messageFile.getConfig().getString("TAG");
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
        String className = message.toString() + (ConfigHelper.DEBUG.getBoolean() ? " (" + message.getClass().getName() + ")" : "");
		return returnString == null ? new String[]{className} : returnString;
	}
}