/*
 * StBukkitLib - SignUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import com.stealthyone.mcb.stbukkitlib.lib.signs.BetterSign;
import com.stealthyone.mcb.stbukkitlib.lib.signs.BetterSignPost;
import com.stealthyone.mcb.stbukkitlib.lib.signs.BetterWallSign;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public final class SignUtils {

    public final static boolean isBlockSign(Block block) {
        return block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN;
        //return block.getState() instanceof Sign;
    }

    public final static Sign getSign(Block block) {
        if (!isBlockSign(block)) {
            return null;
        } else {
            return (Sign) block.getState();
        }
    }

    /**
     * Gets a sign at a given location
     * @param location
     * @return Null if the block at the location isn't a sign
     */
    public final static Sign getSign(Location location) {
        Block block = location.getBlock();
        if (block == null || !isBlockSign(block)) {
            return null;
        } else {
            return getSign(block);
        }
    }

    /**
     * Returns a BetterSign cast for a sign
     * @param block Block representing the sign
     * @return Null if block isn't a sign
     */
    public final static BetterSign getBetterSign(Block block) {
        Material type = block.getType();
        if (type == Material.SIGN_POST) {
            return new BetterSignPost(block);
        } else if (type == Material.WALL_SIGN) {
            return new BetterWallSign(block);
        } else {
            return null;
        }
    }

}