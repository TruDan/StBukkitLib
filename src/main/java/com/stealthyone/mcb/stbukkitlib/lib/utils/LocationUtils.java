/*
 * StBukkitLib
 * Copyright (C) 2014 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/bukkit>
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
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> locationListToStringList(List<Location> locList, boolean ignoreYawPitch) {
        List<String> stringList = new ArrayList<>();
        for (Location location : locList) {
            stringList.add(locationToString(location, ignoreYawPitch));
        }
        return stringList;
    }

    public static List<Location> stringListToLocationList(List<String> stringList) {
        List<Location> locList = new ArrayList<>();
        for (String rawLoc : stringList) {
            locList.add(stringToLocation(rawLoc));
        }
        return locList;
    }

}