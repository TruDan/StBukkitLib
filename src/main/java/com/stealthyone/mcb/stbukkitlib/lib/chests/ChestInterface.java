package com.stealthyone.mcb.stbukkitlib.lib.chests;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;

public class ChestInterface implements InventoryHolder {

	protected final Inventory inventory;
	
	protected final ConfigurationSection storageSection;
	protected final YamlFileManager storageFile;
	
	public ChestInterface(final int size, final String title) {
		this(size, title, null, null);
	}
	
	public ChestInterface(final ConfigurationSection conf, final YamlFileManager file) {
		this(conf.getInt("size"), conf.getString("title"), conf, file);
	}
	
	public ChestInterface(final int size, final String title, final ConfigurationSection conf, final YamlFileManager file) {
		inventory = Bukkit.createInventory(this, size, title);
		storageSection = conf;
		storageFile = file;
	}
	
	public final Inventory getInventory() {
		return inventory;
	}
	
}