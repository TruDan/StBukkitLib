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
package com.stealthyone.mcb.stbukkitlib.lib.signs;

import com.stealthyone.mcb.stbukkitlib.lib.utils.LocationUtils;
import com.stealthyone.mcb.stbukkitlib.lib.utils.SignUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

import java.util.List;

public class BetterSign {

    protected String location;

    protected Sign currentSign = null;

    public BetterSign(Block block) throws IllegalArgumentException {
        Validate.notNull(block);

        location = LocationUtils.locationToString(block.getLocation());
        if (!SignUtils.isBlockSign(block)) {
            throw new IllegalArgumentException("Block at " + location + " isn't a sign.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof BetterSign) {
            return ((BetterSign) obj).location.equals(location);
        } else {
            return false;
        }
    }

    public Location getLocation() {
        return LocationUtils.stringToLocation(location);
    }

    public org.bukkit.material.Sign getSignMaterial() {
        return (org.bukkit.material.Sign) getSign().getData();
    }

    public Sign getSign() {
        return currentSign != null ? currentSign : SignUtils.getSign(getLocation().getBlock());
    }

    public BlockFace getFacing() {
        return ((org.bukkit.material.Sign) getSign().getData()).getFacing();
    }

    public void setFacing(BlockFace direction) {
        Validate.notNull(direction, "Direction cannot be null");

        getSignMaterial().setFacingDirection(direction);
        updateSign(false);
    }

    public String[] getLines() {
        return getSign().getLines();
    }

    public void setLine(int index, String value) {
        if (index < 1 || index > 4) throw new IllegalArgumentException("Index must be 1-4");

        getSign().setLine(index - 1, value);
        updateSign(false);
    }

    public void setLines(List<String> lines) {
        Validate.notNull(lines);

        Sign sign = getSign();
        for (int i = 0; i < 4; i++) {
            try {
                sign.setLine(i, lines.get(i));
            } catch (IndexOutOfBoundsException ex) {
                sign.setLine(i, null);
                break;
            }
        }
        updateSign(false);
    }

    public void recreateSign(BlockFace facing, Material type) {
        Validate.notNull(facing, "Facing cannot be null");
        Validate.notNull(type, "Type cannot be null");
        if (type != Material.SIGN_POST || type != Material.WALL_SIGN)
            throw new IllegalArgumentException("Invalid sign type '" + type.toString() + "'");

        Location location = getLocation();
        location.getBlock().setType(type);
        setFacing(facing);
        updateSign(false);
    }

    public void updateSign() {
        updateSign(true);
    }

    public void updateSign(boolean whileEditing) {
        if (!whileEditing && isBeingEdited()) return;
        getSign().update();
    }

    public boolean isBeingEdited() {
        return currentSign != null;
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
            updateSign();
            currentSign = null;
            return true;
        } else {
            return false;
        }
    }

}