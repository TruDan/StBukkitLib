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

import com.stealthyone.mcb.stbukkitlib.commands.CmdStBukkitLib;
import com.stealthyone.mcb.stbukkitlib.commands.CmdVerify;
import com.stealthyone.mcb.stbukkitlib.config.ConfigHelper;
import com.stealthyone.mcb.stbukkitlib.hooks.HookManager;
import com.stealthyone.mcb.stbukkitlib.lib.backend.autosaving.AutosaveManager;
import com.stealthyone.mcb.stbukkitlib.lib.backend.verification.VerificationManager;
import com.stealthyone.mcb.stbukkitlib.lib.items.ItemRightClickable;
import com.stealthyone.mcb.stbukkitlib.lib.messages.MessageRetriever;
import com.stealthyone.mcb.stbukkitlib.lib.updates.UpdateChecker;
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
    private UpdateChecker updateChecker;

	private Map<String, ItemRightClickable> rightClickableItems = new HashMap<String, ItemRightClickable>();

    private HookManager hookManager;
    private AutosaveManager autosaveManager;
    private VerificationManager verificationManager;

	@Override
	public final void onLoad() {
		logger = getServer().getLogger();
		if (!getDataFolder().exists())
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

        hookManager = new HookManager(this);
        autosaveManager = new AutosaveManager(this);
        verificationManager = new VerificationManager(this);

        PluginManager pluginManager = getServer().getPluginManager();

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
        //getCommand("debug").setExecutor(new CmdDebug(this));

        updateChecker = UpdateChecker.scheduleForMe(this, "http://dev.bukkit.org/server-mods/stbukkitlib/files.rss");

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

    public final UpdateChecker getUpdateChecker() {
        return updateChecker;
    }

	public final Map<String, ItemRightClickable> getRightClickableItems() {
		return rightClickableItems;
	}
	
	public final void registerRightClickableItem(ItemRightClickable item) {
		rightClickableItems.put(item.getName(), item);
		Log.info("Registered right clickable item: " + item.getClass().getName());
	}

    public final HookManager getHookManager() {
        return hookManager;
    }

    public final AutosaveManager getAutosaveManager() {
        return autosaveManager;
    }

    public final VerificationManager getVerificationManager() {
        return verificationManager;
    }

}