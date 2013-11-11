package com.stealthyone.mcb.stbukkitlib.backend.hooks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
import java.util.List;

public class HookBackend {

    private StBukkitLib plugin;

    private boolean vanishHook;
    private PermissionManager permEx;

    private Economy economy;
    private Vault vault;

    private WorldEditPlugin worldedit;
    private WorldGuardPlugin worldguard;

    public HookBackend(StBukkitLib plugin) {
        this.plugin = plugin;
        loadHooks();
    }

    private final void loadHooks() {
        List<String> unhookedPlugins = new ArrayList<String>();
        PluginManager pluginManager = Bukkit.getPluginManager();

        Plugin rawPermEx = pluginManager.getPlugin("PermissionsEx");
        if (rawPermEx != null) {
            permEx = PermissionsEx.getPermissionManager();
            Log.info("Hooked with " + rawPermEx.getName() + " v" + rawPermEx.getDescription().getVersion());
        } else {
            permEx = null;
            unhookedPlugins.add("PermissionsEx");
        }

        Plugin rawVanish = pluginManager.getPlugin("VanishNoPacket");
        if (rawVanish != null) {
            vanishHook = true;
            Log.info("Hooked with " + rawVanish.getName() + " v" + rawVanish.getDescription().getVersion());
        } else {
            vanishHook = false;
            unhookedPlugins.add("VanishNoPacket");
        }

        Plugin rawVault = pluginManager.getPlugin("Vault");
        if (rawVault != null) {
            Log.info("Hooked with " + rawVault.getName() + " v" + rawVault.getDescription().getVersion());
            RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
            if (economyProvider != null) {
                economy = economyProvider.getProvider();
                Plugin ecoPlugin = economyProvider.getPlugin();
                Log.info("Hooked via Vault with economy plugin " + ecoPlugin.getName() + " v" + ecoPlugin.getDescription().getVersion());
            } else {
                economy = null;
                Log.info("Unable to find economy plugin via Vault API.");
                unhookedPlugins.add("Vault-Economy plugin");
            }
        } else {
            unhookedPlugins.add("Vault");
        }

        Plugin rawWorldedit = pluginManager.getPlugin("WorldEdit");
        if (rawWorldedit != null) {
            worldedit = (WorldEditPlugin) rawWorldedit;
            Log.info("Hooked with " + worldedit.getName() + " v" + worldedit.getDescription().getVersion());
        } else {
            worldedit = null;
            unhookedPlugins.add("WorldEdit");
        }


        Plugin rawWorldguard = pluginManager.getPlugin("WorldGuard");
        if (rawWorldguard != null) {
            worldguard = (WorldGuardPlugin) rawWorldguard;
            Log.info("Hooked with " + worldguard.getName() + " v" + worldguard.getDescription().getVersion());
        } else {
            worldguard = null;
            unhookedPlugins.add("WorldGuard");
        }

        if (unhookedPlugins.size() > 0) {
            Log.info("Unable to hook with optional dependencies (" + unhookedPlugins.size() + "): " + unhookedPlugins.toString().replace("[", "").replace("]", ""));
        }
    }

    public final PermissionManager getPermissionsEx() {
        return permEx;
    }

    public final boolean hookedWithVanishNoPacket() {
        return vanishHook;
    }

    public final WorldEditPlugin getWorldEdit() {
        return worldedit;
    }

    public final WorldGuardPlugin getWorldGuard() {
        return worldguard;
    }

    public final Economy getVaultEconomy() {
        return economy;
    }

}