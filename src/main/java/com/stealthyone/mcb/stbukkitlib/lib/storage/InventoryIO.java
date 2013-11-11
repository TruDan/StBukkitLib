/*
 * StBukkitLib - InventoryIO
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.storage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class InventoryIO {

    /**
     * Saves an inventory to a configuration section. File save function should be called after this
     *
     * @param inventory Inventory to save
     * @param config    ConfigurationSection to save inventory in
     */
    public final static void saveToConfigSection(Inventory inventory, ConfigurationSection config) {
        config.set("title", inventory.getName());
        config.set("size", inventory.getSize());
        config.set("items", inventory.getContents());
    }

    /**
     * Loads from configuration section by using 'title', 'size', and 'items' as the sections it reads
     *
     * @param config ConfigurationSection to load from
     * @return New inventory instance that was just loaded
     */
    public final static Inventory loadFromConfigSection(ConfigurationSection config) {
        String title = config.getString("title");
        int size = config.getInt("size");
        Inventory inventory = title.equalsIgnoreCase("") ? Bukkit.createInventory(null, size) : Bukkit.createInventory(null, size, title);

        List<?> list = config.getList("items");
        for (int i = 0; i < size; i++) {
            if (list.get(i) != null)
                inventory.setItem(i, (ItemStack) list.get(i));
        }
        return inventory;
    }

    public final static List<ItemStack> getItemstackList(List rawList) {
        List<ItemStack> returnList = new ArrayList<ItemStack>();
        for (int i = 0; i < rawList.size(); i++) {
            if (rawList.get(i) != null) {
                returnList.add((ItemStack) rawList.get(i));
            } else {
                returnList.add(new ItemStack(Material.AIR));
            }
        }
        return returnList;
    }

}