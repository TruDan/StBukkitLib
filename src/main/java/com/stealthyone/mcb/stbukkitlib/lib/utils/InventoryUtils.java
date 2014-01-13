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
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {

    public static void saveToConfigSection(Inventory inventory, ConfigurationSection config) {
        config.set("title", inventory.getName());
        config.set("size", inventory.getSize());
        config.set("items", inventory.getContents());
    }

    public static Inventory loadFromConfigSection(ConfigurationSection config) {
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

    public static List<ItemStack> getItemstackList(List rawList) {
        List<ItemStack> returnList = new ArrayList<>();
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