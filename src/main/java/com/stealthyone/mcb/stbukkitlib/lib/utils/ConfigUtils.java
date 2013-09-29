/*
 * StBukkitLib - ConfigUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

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
            String[] inputSplit = input.split("|");
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
        return String.format("%s|%s|%s|%s|%s|%s",
                input.getWorld().getName(),
                Integer.toString(input.getBlockX()),
                Integer.toString(input.getBlockY()),
                Integer.toString(input.getBlockZ()),
                Float.toString(input.getYaw()),
                Float.toString(input.getPitch())
        );
    }

}