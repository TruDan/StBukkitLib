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
package com.stealthyone.mcb.stbukkitlib.lib.messages;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvMessageManager extends MessageManager {

    public AdvMessageManager(JavaPlugin plugin) {
        super(plugin);
    }

    public AdvMessageManager(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    public String getMessage(String name, String... replacements) {
        String[] nameSplit = name.split(".");
        String message;
        try {
            message = messages.get(nameSplit[0]).get(nameSplit[1]);
        } catch (NullPointerException ex) {
            return ChatColor.RED + "Undefined message '" + name + "'";
        }

        for (String replacement : replacements) {
            String[] split = replacement.split("\\|");
            message = message.replace(split[0], split[1]);
        }
        return message;
    }

}