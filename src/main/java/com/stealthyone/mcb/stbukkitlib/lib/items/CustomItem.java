package com.stealthyone.mcb.stbukkitlib.lib.items;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomItem extends ItemStack {

    public final static void registerRightClickableItem(ItemRightClickable item) {
        StBukkitLib.getInstance().registerRightClickableItem(item);
    }

    /**
     * Creates a CustomItem cast for an ItemStack
     *
     * @param stack Input ItemStack
     * @throws IllegalArgumentException Thrown if input ItemStack is null
     */
    public CustomItem(ItemStack stack) {
        super(stack);
    }

    public CustomItem(Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    public CustomItem(Material type, int amount) {
        super(type, amount);
    }

    public CustomItem(Material type) {
        this(type, 1);
    }

    /**
     * Sets the display name of the item
     *
     * @param name Name to set for the item
     */
    public final void setName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(name);
        setItemMeta(meta);
    }

    /**
     * Gets the display name of the item
     *
     * @return Null if item doesn't have a display name
     */
    public String getName() {
        return getItemMeta().getDisplayName();
    }

    /**
     * REMOVES OLD LORE and adds the new lore
     *
     * @param lore List of strings to set as lore. New item in list = new line
     */
    public final void setLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        Log.debug("Before set: " + meta.getLore().toString());
        meta.setLore(lore);
        Log.debug("After set: " + meta.getLore().toString());
        setItemMeta(meta);
    }

    /**
     * ADDS to the existing lore of the item
     * If item doesn't have lore, creates it
     *
     * @param lore
     */
    public final void addLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        List<String> currentLore;
        if (meta.hasLore()) {
            currentLore = meta.getLore();
            currentLore.addAll(lore);
            setItemMeta(meta);
        } else {
            setLore(lore);
        }
    }

    /**
     * Returns the existing lore of the item
     *
     * @return Null if item doesn't have any lore
     */
    public final List<String> getLore() {
        return getItemMeta().getLore();
    }

}