package com.stealthyone.mcb.stbukkitlib;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.config.ConfigHelper;

public final class UpdateCheckRunnable implements Runnable {
		
	private StBukkitLib plugin;
	
	public UpdateCheckRunnable(StBukkitLib plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public final void run() {
		if (ConfigHelper.CHECK_FOR_UPDATES.getBoolean()) {
			UpdateChecker updateChecker = new UpdateChecker(plugin);
			updateChecker.checkForUpdates();
			if (updateChecker.isUpdateNeeded()) {
				Log.info("Found a different version on BukkitDev! (Remote: " + updateChecker.getNewVersion() + " | Current: " + plugin.getVersion() + ")");
				Log.info("You can download it from: " + updateChecker.getVersionLink());
			}
		} else {
			Log.info("Update checker is disabled, enable in config for auto update checking.");
			Log.info("You can also check for updates by typing the version command.");
		}
	}
	
}