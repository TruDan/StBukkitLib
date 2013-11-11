package com.stealthyone.mcb.stbukkitlib.lib.signs;

import com.stealthyone.mcb.stbukkitlib.lib.utils.SignUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

public abstract class BetterSign {

    protected Location location;

    protected Sign currentSign = null;

    public BetterSign(Block block) throws IllegalArgumentException {
        if (!SignUtils.isBlockSign(block)) {
            throw new IllegalArgumentException();
        }

        this.location = block.getLocation();
    }

    public Location getLocation() {
        return location;
    }

    public boolean startEditing() {
        if (!isBeingEdited()) {
            currentSign = getSign();
            return true;
        } else {
            return false;
        }
    }

    public boolean endEditing() {
        if (isBeingEdited()) {
            currentSign.update();
            currentSign = null;
            return true;
        } else {
            return false;
        }
    }

    public boolean isBeingEdited() {
        return currentSign != null;
    }

    public final Sign getSign() {
        Block block = location.getBlock();
        return isBeingEdited() ? currentSign : (SignUtils.isBlockSign(block) ? SignUtils.getSign(block) : null);
    }

    public String[] getLines() {
        Sign sign = getSign();
        return (sign != null) ? sign.getLines() : null;
    }

    public boolean setLine(int index, String value) throws IllegalArgumentException {
        if (index < 0 || index > 3) {
            throw new IllegalArgumentException();
        }

        Sign sign = getSign();
        if (sign == null) {
            return false;
        } else {
            sign.setLine(index, value);
            if (!isBeingEdited()) sign.update();
            return true;
        }
    }

    public BlockFace getFacing() {
        Sign sign = getSign();
        if (sign == null) {
            return null;
        } else {
            org.bukkit.material.Sign signMat = (org.bukkit.material.Sign) sign.getData();
            return signMat.getFacing();
        }
    }

    public boolean setFacing(BlockFace direction) throws IllegalArgumentException {
        if (direction == null) {
            throw new IllegalArgumentException();
        }

        Sign sign = getSign();
        if (sign == null) {
            return false;
        } else {
            org.bukkit.material.Sign signMat = (org.bukkit.material.Sign) sign.getData();
            signMat.setFacingDirection(direction);
            if (!isBeingEdited()) sign.update();
            return true;
        }
    }

}