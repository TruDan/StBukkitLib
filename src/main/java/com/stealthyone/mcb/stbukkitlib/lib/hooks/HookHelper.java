package com.stealthyone.mcb.stbukkitlib.lib.hooks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.hooks.HookManager;

public final class HookHelper {

    private final static HookManager getHookManager() {
        return StBukkitLib.getInstance().getHookManager();
    }

    /**
     * Returns a WorldEdit hook
     * @return Null if not hooked
     */
    public final static WorldEditPlugin getWorldEdit() {
        return getHookManager().getWorldEdit();
    }

    /**
     * Returns a WorldGuard hook
     * @return Null if not hooked
     */
    public final static WorldGuardPlugin getWorldGuard() {
        return getHookManager().getWorldGuard();
    }

}