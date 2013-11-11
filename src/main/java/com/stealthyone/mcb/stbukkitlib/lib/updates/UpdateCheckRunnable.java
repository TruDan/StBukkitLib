package com.stealthyone.mcb.stbukkitlib.lib.updates;

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
        updateChecker.checkForUpdates();
    }

}