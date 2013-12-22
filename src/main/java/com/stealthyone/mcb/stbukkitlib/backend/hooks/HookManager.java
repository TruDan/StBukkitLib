/*
 * StBukkitLib - Set of useful Bukkit-related classes
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://google.com/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.stealthyone.mcb.stbukkitlib.backend.hooks;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.backend.exceptions.UnloadedHookException;
import org.apache.commons.lang.Validate;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class HookManager {

    private StBukkitLib plugin;

    private Map<String, PluginHook> hooks = new HashMap<>();
    private Map<String, Boolean> hookStatuses = new HashMap<>();

    public HookManager(StBukkitLib plugin) {
        this.plugin = plugin;
        registerHook(new VanishNoPacketHook());
        registerHook(new VaultHook());
        registerHook(new InSignsHook());
    }

    public boolean registerHook(PluginHook hook) {
        Validate.notNull(hook, "Hook cannot be null");

        String name = hook.getName().toLowerCase();
        if (hooks.containsKey(name)) {
            return false;
        }

        hooks.put(name, hook);
        try {
            hook.load();
            hookStatuses.put(name, true);
            return true;
        } catch (UnloadedHookException ex) {
            hookStatuses.put(name, false);
            return false;
        }
    }

    public boolean isHookActive(PluginHook hook) {
        Validate.notNull(hook, "Hook cannot be null");

        return hookStatuses.get(hook.getName().toLowerCase());
    }

    public boolean validateHook(JavaPlugin plugin, String hookName) {
        PluginHook hook;
        try {
             hook = getHook(hookName);
        } catch (UnloadedHookException ex) {
            plugin.getLogger().log(Level.WARNING, "Unable to hook with plugin: '" + hookName + "' -> hook not loaded (is the plugin installed on the server?)");
            return false;
        }

        if (hook == null) {
            plugin.getLogger().log(Level.WARNING, "Unregistered or nonexistant hook '" + hookName + "'");
            return false;
        } else {
            plugin.getLogger().log(Level.INFO, "Hooked with plugin: '" + hook.getName() + "' (v" + hook.getVersion() + ")");
            return true;
        }
    }

    public PluginHook getHook(String name) {
        Validate.notNull(name, "Name cannot be null");

        PluginHook hook = hooks.get(name.toLowerCase());
        if (hook == null) {
            return null;
        } else if (!isHookActive(hook)) {
            throw new UnloadedHookException();
        }
        return hooks.get(name.toLowerCase());
    }

    public InSignsHook getInSigns() {
        return (InSignsHook) getHook("InSigns");
    }

    public VanishNoPacketHook getVanishNoPacket() {
        return (VanishNoPacketHook) getHook("VanishNoPacket");
    }

    public VaultHook getVault() {
        return (VaultHook) getHook("Vault");
    }

}