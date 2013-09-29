package com.stealthyone.mcb.stbukkitlib.lib.items;

import org.bukkit.event.player.PlayerInteractEvent;

public interface ItemRightClickable {

    String getName();

    void rightClicked(PlayerInteractEvent e);

}