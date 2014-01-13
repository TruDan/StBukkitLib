package com.stealthyone.mcb.stbukkitlib.api;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.players.PlayerUuidManager;

public class Stbl {

    public static PlayerUuidManager getUuidManager() {
        return StBukkitLib.getInstance().getIdManager();
    }

}