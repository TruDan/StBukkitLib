package com.stealthyone.mcb.stbukkitlib.lib.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class SignUtils {

    public static boolean isBlockSign(Block block) {
        Validate.notNull(block, "Block cannot be null");

        return block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN;
    }

}