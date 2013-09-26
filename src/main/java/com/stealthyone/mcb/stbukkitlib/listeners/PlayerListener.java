package com.stealthyone.mcb.stbukkitlib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.items.ItemRightClickable;

public final class PlayerListener implements Listener {

	private StBukkitLib plugin;
	
	public PlayerListener(StBukkitLib plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public final void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			ItemStack clickedItem = e.getItem();
			if (clickedItem != null) {
				String clickedItemName = clickedItem.getItemMeta().getDisplayName();
				if (clickedItemName != null) {
					ItemRightClickable registeredType = plugin.getRightClickableItems().get(clickedItemName);
					if (registeredType != null)
						registeredType.rightClicked(e);
				}
			}
		}
	}
	
}