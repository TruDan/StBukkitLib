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