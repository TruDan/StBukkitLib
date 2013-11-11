package com.stealthyone.mcb.stbukkitlib.lib.signs;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BetterWallSign extends BetterSign {

    public BetterWallSign(Block block) throws IllegalArgumentException {
        super(block);

        if (block.getType() != Material.WALL_SIGN) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean setFacing(BlockFace direction) throws IllegalArgumentException {
        if (direction == null) {
            throw new IllegalArgumentException();
        }

        switch (direction) {
            case NORTH: case EAST: case SOUTH: case WEST:
                return super.setFacing(direction);
            default:
                throw new IllegalArgumentException();
        }
    }

}