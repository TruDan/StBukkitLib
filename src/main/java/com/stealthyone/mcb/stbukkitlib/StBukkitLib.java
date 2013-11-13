/*
 * StBukkitLib
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * This program is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 2 of the License, or 
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Overview and simplified explanation of the license:
 * <http://www.tldrlegal.com/license/gnu-general-public-license-v2-(gpl-2)>
 */
package com.stealthyone.mcb.stbukkitlib;

import com.stealthyone.mcb.stbukkitlib.backend.autosaving.AutosaveBackend;
import com.stealthyone.mcb.stbukkitlib.backend.help.HelpBackend;
import com.stealthyone.mcb.stbukkitlib.backend.hooks.HookBackend;
import com.stealthyone.mcb.stbukkitlib.backend.verification.VerificationBackend;
import com.stealthyone.mcb.stbukkitlib.commands.CmdStBukkitLib;
import com.stealthyone.mcb.stbukkitlib.commands.CmdVerify;
import com.stealthyone.mcb.stbukkitlib.config.ConfigHelper;
import com.stealthyone.mcb.stbukkitlib.lib.help.HelpAPI;
import com.stealthyone.mcb.stbukkitlib.lib.help.HelpManager;
import com.stealthyone.mcb.stbukkitlib.lib.items.ItemRightClickable;
import com.stealthyone.mcb.stbukkitlib.lib.messages.MessageRetriever;
import com.stealthyone.mcb.stbukkitlib.lib.updates.Updater;
import com.stealthyone.mcb.stbukkitlib.listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class StBukkitLib extends JavaPlugin {

    public final static class Log {

        public final static void debug(String msg) {
            if (ConfigHelper.DEBUG.getBoolean())
                instance.logger.log(Level.INFO, String.format("[%s DEBUG] %s", instance.getName(), msg));
        }

        public final static void info(String msg) {
            instance.logger.log(Level.INFO, String.format("[%s] %s", instance.getName(), msg));
        }

        public final static void warning(String msg) {
            instance.logger.log(Level.WARNING, String.format("[%s] %s", instance.getName(), msg));
        }

        public final static void severe(String msg) {
            instance.logger.log(Level.SEVERE, String.format("[%s] %s", instance.getName(), msg));
        }

    }

    private static StBukkitLib instance;

    {
        instance = this;
    }

    public final static StBukkitLib getInstance() {
        return instance;
    }

    private Logger logger;

    private MessageRetriever messageRetriever;
    private HelpManager helpManager;
    private Updater updater;

    private Map<String, ItemRightClickable> rightClickableItems = new HashMap<String, ItemRightClickable>();
    private Map<String, Class<? extends ItemRightClickable>> rightClickableItemClasses = new HashMap<String, Class<? extends ItemRightClickable>>();

    private HelpBackend helpBackend;
    private HookBackend hookBackend;
    private AutosaveBackend autosaveBackend;
    private VerificationBackend verificationBackend;

    @Override
    public final void onLoad() {
        logger = getServer().getLogger();
        getDataFolder().mkdir();
    }

    @Override
    public final void onEnable() {
        /* Setup config */
        saveDefaultConfig();
        getConfig().options().copyDefaults(false);
        saveConfig();
		
		/* Setup important plugin parts */
        messageRetriever = new MessageRetriever(this);

        helpBackend = new HelpBackend(this);
        hookBackend = new HookBackend(this);
        autosaveBackend = new AutosaveBackend(this);
        verificationBackend = new VerificationBackend(this);

        PluginManager pluginManager = getServer().getPluginManager();

        helpManager = HelpAPI.registerHelp(this);

		/* Register listeners */
        pluginManager.registerEvents(new PlayerListener(this), this);
		
		/* Register commands */
        getCommand("stbukkitlib").setExecutor(new CmdStBukkitLib(this));
        if (!ConfigHelper.DISABLE_VERIFICATION.getBoolean()) {
            CmdVerify cmdVerify = new CmdVerify(this);
            getCommand("yes").setExecutor(cmdVerify);
            getCommand("no").setExecutor(cmdVerify);
            Log.info("Verification API enabled.");
        } else {
            Log.info("Verification API disabled.");
        }

        if (!ConfigHelper.DISABLE_HELP.getBoolean()) {
            Log.info("Help API enabled.");
        } else {
            Log.info("Help API disabled.");
        }
        //getCommand("debug").setExecutor(new CmdDebug(this));

        Log.info(String.format("%s v%s by Stealth2800 enabled.", getName(), getVersion()));
    }

    @Override
    public final void onDisable() {
        Log.info(String.format("%s v%s by Stealth2800 disabled.", getName(), getVersion()));
    }

    public final String getVersion() {
        return getDescription().getVersion();
    }

    public final MessageRetriever getMessageManager() {
        return messageRetriever;
    }

    public final HelpManager getHelpIndexer() {
        return helpManager;
    }

    public final Updater getUpdater() {
        return updater;
    }

    @Deprecated
    public final Map<String, ItemRightClickable> getRightClickableItems() {
        return rightClickableItems;
    }

    public final Map<String, Class<? extends ItemRightClickable>> getRightClickableItemClasses() {
        return rightClickableItemClasses;
    }

    @Deprecated
    public final void registerRightClickableItem(ItemRightClickable item) {
        rightClickableItems.put(item.getName(), item);
        Log.info("Registered right clickable item: " + item.getClass().getName());
    }

    public final void registerRightClickableItem(Class<? extends ItemRightClickable> clazz) {
        rightClickableItemClasses.put(clazz.getName(), clazz);
        Log.info("Registered right clickable item class: " + clazz.getName());
    }

    public final HelpBackend getHelpBackend() {
        return helpBackend;
    }

    public final HelpManager getHelpManager() {
        return helpManager;
    }

    public final HookBackend getHookBackend() {
        return hookBackend;
    }

    public final AutosaveBackend getAutosaveBackend() {
        return autosaveBackend;
    }

    public final VerificationBackend getVerificationBackend() {
        return verificationBackend;
    }

}