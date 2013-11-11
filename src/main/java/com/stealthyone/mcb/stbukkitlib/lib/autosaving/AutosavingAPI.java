package com.stealthyone.mcb.stbukkitlib.lib.autosaving;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.backend.autosaving.AutosaveBackend;
import org.bukkit.plugin.java.JavaPlugin;

public class AutosavingAPI {

    private final static AutosaveBackend getAutosaveManager() {
        return StBukkitLib.getInstance().getAutosaveBackend();
    }

    /**
     * Registers an autosavable
     *
     * @param plugin      Plugin owning autosavable
     * @param name        Name of autosavable
     * @param autosavable Autosavable object
     * @param seconds     Seconds to autosave
     */
    public final static void registerAutosavable(JavaPlugin plugin, String name, Autosavable autosavable, int seconds) {
        getAutosaveManager().registerAutosavable(plugin, name, autosavable, seconds);
    }

}