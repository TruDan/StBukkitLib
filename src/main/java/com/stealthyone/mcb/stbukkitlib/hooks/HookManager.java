package com.stealthyone.mcb.stbukkitlib.hooks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HookManager {

    private StBukkitLib plugin;

    private WorldEditPlugin worldedit;
    private WorldGuardPlugin worldguard;

    public HookManager(StBukkitLib plugin)  {
        this.plugin = plugin;
    }

    public final void loadHooks() {
        List<String> unhookedPlugins = new ArrayList<String>();

        Plugin rawWorldedit = Bukkit.getPluginManager().getPlugin("WorldEdit");
        if (rawWorldedit != null) {
            worldedit = (WorldEditPlugin) rawWorldedit;
            Log.info("Hooked with " + worldedit.getName() + " v" + worldedit.getDescription().getVersion());
        } else {
            worldedit = null;
            unhookedPlugins.add("WorldEdit");
        }


        Plugin rawWorldguard = Bukkit.getPluginManager().getPlugin("WorldGuard");
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

    public final WorldEditPlugin getWorldEdit() {
        return worldedit;
    }

    public final WorldGuardPlugin getWorldGuard() {
        return worldguard;
    }

}