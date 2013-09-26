/*
 * StBukkitLib - ItemUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import com.stealthyone.mcb.stbukkitlib.lib.utils.exceptions.InvalidMaterialException;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemUtils {

    /**
     * Returns a colored leather armor piece
     * @param material
     * @param color
     * @return
     * @throws InvalidMaterialException if material is not a leather armor piece
     */
    public final static ItemStack getColoredLeatherArmor(Material material, Color color) {
        if (!(material == Material.LEATHER_BOOTS || material == Material.LEATHER_CHESTPLATE || material == Material.LEATHER_HELMET || material == Material.LEATHER_LEGGINGS)) {
            throw new InvalidMaterialException();
        } else {
            ItemStack returnItem = new ItemStack(material);
            LeatherArmorMeta meta = (LeatherArmorMeta) returnItem.getItemMeta();
            meta.setColor(color);
            return returnItem;
        }
    }

}