/*
 * StBukkitLib - JarYamlFileManager
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.storage;

import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class intended for retrieving configuration of files located in the plugin .jar itself.
 * @author Stealth2800
 */
public class JarYamlFileManager {

	private FileConfiguration config;
	
	/**
	 * Constructs a new JarYamlFileManager using a specified file name.
	 * @param plugin
	 * @param fileName Name of file. Include the extension as well as the name (typically .yml).
	 */
	public JarYamlFileManager(JavaPlugin plugin, String fileName) {
		InputStream stream = plugin.getResource(fileName);
		if (stream != null) {
			config = YamlConfiguration.loadConfiguration(stream);
		}
	}
	
	/**
	 * Returns the configuration of the file located in the .jar.
	 * @return FileConfiguration of file.
	 */
	public final FileConfiguration getConfig() {
		return config;
	}
	
}