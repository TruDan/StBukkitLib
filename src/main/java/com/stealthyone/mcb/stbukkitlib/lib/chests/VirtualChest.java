package com.stealthyone.mcb.stbukkitlib.lib.chests;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VirtualChest {

    private Inventory inventory;

    public VirtualChest(int size) {
        Bukkit.createInventory(null, size);
    }

    public VirtualChest(int size, String title) {
        Bukkit.createInventory(null, size, title);
    }

    public VirtualChest setContents(ItemStack[] items) {
        inventory.setContents(items);
        return this;
    }

    public ItemStack[] getContents() {
        return inventory.getContents();
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

}