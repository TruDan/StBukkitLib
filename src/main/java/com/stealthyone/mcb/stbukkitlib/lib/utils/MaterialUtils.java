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

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialUtils {

    public static ItemStack rawMaterialToItem(String materialName) {
        return rawMaterialToItem(materialName, 1);
    }

    public static ItemStack rawMaterialToItem(String materialName, int amount) {
        Validate.notNull(materialName, "Material name cannot be null.");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than 0.");

        String[] split = materialName.split(":");
        Material material;
        short damageValue = 0;
        material = Material.getMaterial(split[0].toUpperCase());
        if (material == null) {
            throw new IllegalArgumentException("Invalid material '" + split[0] + "'");
        }

        if (split.length > 1) {
            try {
                damageValue = Short.parseShort(split[1]);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Damage value must be a number.");
            }
        }

        return new ItemStack(material, amount, damageValue);
    }

}