/*
 * StBukkitLib - ConfigUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigUtils {

    /**
     * Gets a location from a string
     * Format: world|x|y|z|yaw|pitch
     * Format: String|int|int|int|float|float
     *
     * @param input
     * @return Null if input is null or has 0 length
     */
    public final static Location getLocation(String input) {
        if (input == null || input.length() == 0) {
            return null;
        } else {
            Log.debug("getLocation input: " + input);
            String[] inputSplit = input.split(",");
            Log.debug("getLocation inputSplit: " + Arrays.toString(inputSplit));
            return new Location(
                    Bukkit.getWorld(inputSplit[0]),
                    Integer.parseInt(inputSplit[1]),
                    Integer.parseInt(inputSplit[2]),
                    Integer.parseInt(inputSplit[3]),
                    Float.parseFloat(inputSplit[4]),
                    Float.parseFloat(inputSplit[5])
            );
        }
    }

    /**
     * Converts a location to a string
     * Format: world|x|y|z|yaw|pitch
     * Format: String|int|int|int|float|float
     *
     * @param input
     * @return
     */
    public final static String locationToString(Location input) {
        if (input == null) {
            return null;
        } else {
            return String.format("%s,%s,%s,%s,%s,%s",
                    input.getWorld().getName(),
                    Integer.toString(input.getBlockX()),
                    Integer.toString(input.getBlockY()),
                    Integer.toString(input.getBlockZ()),
                    Float.toString(input.getYaw()),
                    Float.toString(input.getPitch())
            );
        }
    }

    /**
     * Saves a list of items to a specified key in a configuration section
     * @param config Base configuration section
     * @param key Name of list to save
     * @param items List of items to save
     * @throws IllegalArgumentException Thrown if config, key, or items is null
     */
    public final static void setItemList(ConfigurationSection config, String key, ItemStack[] items) throws IllegalArgumentException {
        if (config == null || key == null || items == null) {
            throw new IllegalArgumentException();
        }
        config.set(key, items);
    }

    /**
     * Returns a list of items from a configuration section
     * @param config Configuration section to retrieve item list from
     * @param key Name of list
     * @return ItemStack array of items from the configuration section
     * @throws IllegalArgumentException Thrown if config or key is null or the list represented by the key doesn't exist in the config
     */
    public final static ItemStack[] getItemList(ConfigurationSection config, String key) throws IllegalArgumentException {
        if (config == null || key == null) {
            throw new IllegalArgumentException("Config or key is null");
        }

        List<?> list = config.getList(key);
        if (list == null) {
            throw new IllegalArgumentException("List doesn't exist or contains nothing in config");
        }

        List<ItemStack> returnList = new ArrayList<ItemStack>();
        for (Object object : list) {
            returnList.add((ItemStack) object);
        }
        return returnList.toArray(new ItemStack[returnList.size()]);
    }

}