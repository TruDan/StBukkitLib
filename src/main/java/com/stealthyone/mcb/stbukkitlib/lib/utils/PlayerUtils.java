package com.stealthyone.mcb.stbukkitlib.lib.utils;

import com.stealthyone.mcb.stbukkitlib.api.Stbl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUtils {

    public static Player getPlayerByUuid(UUID uuid) {
        return getPlayerByUuid(uuid.toString());
    }

    public static Player getPlayerByUuid(String uuid) {
        String name = Stbl.getUuidManager().getName(uuid);
        return name == null ? null : Bukkit.getPlayerExact(name);
    }

}