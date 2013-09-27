package com.stealthyone.mcb.stbukkitlib.lib.chests;

import com.stealthyone.mcb.stbukkitlib.lib.storage.InventoryIO;
import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import com.stealthyone.mcb.stbukkitlib.lib.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ChestInterface implements InventoryHolder {

	protected Inventory inventory;
	
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

    public final void loadFromStorage() {
        if (storageSection == null)
            return;
        try {
            if (storageSection.getList("items").size() == 0) {
                return;
            }
        } catch (NullPointerException e) {
            return;
        }
        inventory = InventoryIO.loadFromConfigSection(storageSection);
    }

    public final void saveToStorage() {
        saveToStorage(true);
    }

    public final void saveToStorage(boolean saveFile) {
        if (storageSection == null)
            return;
        InventoryIO.saveToConfigSection(inventory, storageSection);
        if (saveFile)
            storageFile.saveFile();
    }

	public final Inventory getInventory() {
		return inventory;
	}

    public final void openInventory(Player player) {
        player.openInventory(inventory);
    }

    public final int getContentCount() {
        return InventoryUtils.getItemCount(inventory);
    }

}