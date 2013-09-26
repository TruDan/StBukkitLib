/*
 * StBukkitLib - YamlFileManager
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.storage;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;

/**
 * Class intended for managing .yml files simply.
 * @author Stealth2800
 */
public class YamlFileManager {

	private File customFile;
	private FileConfiguration customConfig;
	
	/**
	 * Constructs a new instance of a YamlFileManager using the path of the file.
	 * @param filePath Path of File to use.
	 */
	public YamlFileManager(String filePath) {
		this(new File(filePath));
	}
	
	/**
	 * Constructs a new instance of a YamlFileManager using the file itself.
	 * @param file File to use.
	 */
	public YamlFileManager(File file) {
		this.customFile = file;
		this.reloadConfig();
	}
	
	/**
	 * Returns the file this instance is managing.
	 * @return Raw File.
	 */
	public final File getFile() {
		return customFile;
	}
	
	/**
	 * Returns the configuration of the file.
	 * @return FileConfiguration of file's config.
	 */
	public final FileConfiguration getConfig() {
		if (customConfig == null) {
			this.reloadConfig();
		}
		return customConfig;
	}
	
	/**
	 * Reloads the configuration from the file.
	 * If the file doesn't exist, this method will create it.
	 */
	public final void reloadConfig() {
		if (!customFile.exists()) {
			try {
				customFile.createNewFile();
				saveFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		customConfig = YamlConfiguration.loadConfiguration(customFile);
	}
	
	/**
	 * Saves the file to the disk.
	 */
	public final void saveFile() {
		if (customConfig == null || customFile == null) {
			Log.debug("(saveFile) - customConfig or customFile is null");
        	this.reloadConfig();
        } else {
        	try {
        		customConfig.save(customFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
        }
	}
	
	/**
	 * Method to check if the file is empty or not.
	 * @return True if the file is empty.
	 */
	public final boolean isEmpty() {
		return customConfig.getKeys(false).size() == 0;
	}
	
	public final void copyDefaults(FileConfiguration otherConfig) {
		customConfig.addDefaults(otherConfig);
		customConfig.options().copyDefaults(true);
		saveFile();
	}
	
}