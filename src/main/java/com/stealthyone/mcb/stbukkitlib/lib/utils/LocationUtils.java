package com.stealthyone.mcb.stbukkitlib.lib.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {

    public static String locationToString(Location location, boolean ignoreYawPitch) {
        String returnString = location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ();
        if (!ignoreYawPitch) {
            returnString = returnString + "," + location.getYaw() + "," + location.getPitch();
        }
        return returnString;
    }

    public static Location stringToLocation(String rawLocation) {
        String[] split = rawLocation.split(",");
        if (split.length == 6) {
            return new Location(
                    Bukkit.getWorld(split[0]),
                    Double.parseDouble(split[1]),
                    Double.parseDouble(split[2]),
                    Double.parseDouble(split[3]),
                    Float.parseFloat(split[4]),
                    Float.parseFloat(split[5])
            );
        } else {
            return new Location(
                    Bukkit.getWorld(split[0]),
                    Double.parseDouble(split[1]),
                    Double.parseDouble(split[2]),
                    Double.parseDouble(split[3])
            );
        }
    }

}