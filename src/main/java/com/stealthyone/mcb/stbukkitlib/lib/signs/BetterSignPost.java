package com.stealthyone.mcb.stbukkitlib.lib.signs;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BetterSignPost extends BetterSign {

    public BetterSignPost(Block block) throws IllegalArgumentException {
        super(block);

        if (block.getType() != Material.SIGN_POST) {
            throw new IllegalArgumentException();
        }
    }

}