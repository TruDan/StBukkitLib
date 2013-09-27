package com.stealthyone.mcb.stbukkitlib.lib.updates;

import com.stealthyone.mcb.stbukkitlib.config.ConfigHelper;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class UpdateCheckRunnable implements Runnable {
		
	private JavaPlugin plugin;

    private String updateUrl = "somelink";
    private Logger logger;
    private UpdateChecker updateChecker;

	public UpdateCheckRunnable(JavaPlugin plugin, String updateUrl, Logger logger, UpdateChecker updateChecker) {
		this.plugin = plugin;
        this.updateUrl = updateUrl;
        this.logger = logger;
        this.updateChecker = updateChecker;
	}

    public final UpdateChecker getUpdateChecker() {
        return updateChecker;
    }

	@Override
	public final void run() {
        String logPrefix = "[" + plugin.getName() + "] ";
		if (ConfigHelper.CHECK_FOR_UPDATES.getBoolean()) {
			updateChecker.checkForUpdates();
			if (updateChecker.isUpdateNeeded()) {
				logger.info(logPrefix + "Found a different version on BukkitDev! (Remote: " + updateChecker.getNewVersion() + " | Current: " + plugin.getDescription().getVersion() + ")");
                logger.info(logPrefix + "You can download it from: " + updateChecker.getVersionLink());
			}
		} else {
            logger.info(logPrefix + "Update checker is disabled, enable in config for auto update checking.");
            logger.info(logPrefix + "You can also check for updates by typing the version command.");
		}
	}
	
}