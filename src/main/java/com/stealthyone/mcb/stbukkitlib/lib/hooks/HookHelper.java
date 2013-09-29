package com.stealthyone.mcb.stbukkitlib.lib.hooks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.backend.hooks.HookManager;

public final class HookHelper {

    public final static HookManager getHookManager() {
        return StBukkitLib.getInstance().getHookManager();
    }

    /**
     * Returns whether or not VanishNoPacket is hooked
     *
     * @return
     */
    public final static boolean hookVanishNoPacket() {
        return getHookManager().hoookedWithVanishNoPacket();
    }

    /**
     * Returns a WorldEdit hook
     *
     * @return Null if not hooked
     */
    public final static WorldEditPlugin getWorldEdit() {
        return getHookManager().getWorldEdit();
    }

    /**
     * Returns a WorldGuard hook
     *
     * @return Null if not hooked
     */
    public final static WorldGuardPlugin getWorldGuard() {
        return getHookManager().getWorldGuard();
    }

}