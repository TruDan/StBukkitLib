package com.stealthyone.mcb.stbukkitlib.lib.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtils {

    /**
     * Returns the number of items in an inventory
     * @param inventory
     * @return
     */
    public static int getItemCount(Inventory inventory) {
        int count = 0;
        for (ItemStack item : inventory.getContents())
            if (item != null) count ++;
        return count;
    }

    /**
     * Removes a certain amount of the item in the player's hand
     * @param player
     * @param amount
     */
    public static void removeItemFromHand(Player player, int amount) {
        PlayerInventory pi = player.getInventory();
        ItemStack handItem = pi.getItemInHand();
        if (handItem.getAmount() == 1) {
            pi.setItemInHand(null);
        } else {
            handItem.setAmount(handItem.getAmount() - 1);
            pi.setItemInHand(handItem);
        }
    }

}