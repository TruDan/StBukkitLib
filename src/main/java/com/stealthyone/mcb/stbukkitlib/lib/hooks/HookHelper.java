package com.stealthyone.mcb.stbukkitlib.lib.hooks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.backend.hooks.HookBackend;
import net.milkbowl.vault.economy.Economy;
import ru.tehkode.permissions.PermissionManager;

public final class HookHelper {

    public final static HookBackend getHookManager() {
        return StBukkitLib.getInstance().getHookBackend();
    }

    public final static PermissionManager getPermissionsEx() {
        return getHookManager().getPermissionsEx();
    }

    /**
     * Returns whether or not VanishNoPacket is hooked
     *
     * @return
     */
    public final static boolean hookVanishNoPacket() {
        return getHookManager().hookedWithVanishNoPacket();
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

    public final static Economy getVaultEconomy() {
        return getHookManager().getVaultEconomy();
    }

}