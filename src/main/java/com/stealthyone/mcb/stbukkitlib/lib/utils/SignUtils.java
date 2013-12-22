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

import com.stealthyone.mcb.stbukkitlib.lib.signs.BetterSign;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class SignUtils {

    public static boolean isBlockSign(Block block) {
        Validate.notNull(block, "Block cannot be null");
        return block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN;
    }

    public static Sign getSign(Block block) {
        Validate.notNull(block, "Block cannot be null");
        return !isBlockSign(block) ? null : (Sign) block.getState();
    }

    public static BetterSign getBetterSign(Block block) {
        Validate.notNull(block, "Block cannot be null");
        return !isBlockSign(block) ? null : new BetterSign(block);
    }

}