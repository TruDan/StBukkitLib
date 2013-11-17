package com.stealthyone.mcb.stbukkitlib.lib.updates;

import com.stealthyone.mcb.stbukkitlib.lib.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateChecker {

    public final static UpdateChecker scheduleForMe(JavaPlugin plugin, int pluginId) {
        UpdateChecker updateChecker = new UpdateChecker(plugin, pluginId);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, updateChecker.getRunnable(), 40L, 432000L);
        return updateChecker;
    }

    private JavaPlugin plugin;
    private Logger logger;
    private int pluginId;
    private UpdateCheckRunnable runnable;

    private URL url;
    private boolean updateNeeded = false;
    private String newVersion = "", versionLink = "";

    public UpdateChecker(JavaPlugin plugin, int pluginId) {
        if (plugin == null) throw new IllegalArgumentException();
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.pluginId = pluginId;
        runnable = new UpdateCheckRunnable(this);
        try {
            this.url = new URL("https://api.curseforge.com/servermods/files?projectIds=" + pluginId);
        } catch (MalformedURLException ex) {
            logger.log(Level.SEVERE, "Invalid pluginId '" + pluginId + "'");
            ex.printStackTrace();
        }
    }

    public UpdateCheckRunnable getRunnable() {
        return runnable;
    }

    public boolean isUpdateNeeded() {
        return updateNeeded;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getVersionLink() {
        return versionLink;
    }

    public boolean checkForUpdates() {
        return checkForUpdates(false);
    }

    public boolean checkForUpdates(boolean log) {
        String curVersion = "v" + plugin.getDescription().getVersion();
        if (!plugin.getConfig().getBoolean("Check for updates", false)) {
            if (log) {
                logger.log(Level.INFO, "Update checker is disabled, enable in config for auto update checking.");
                logger.log(Level.INFO, "You can also check for updates by typing the version command.");
            }
            updateNeeded = false;
        } else if (url.toString().equalsIgnoreCase("somelink") || StringUtils.containsMultiple(curVersion, "SNAPSHOT", "BETA", "ALPHA")) {
            if (log) {
                logger.log(Level.INFO, "Currently running a snapshot, beta, or alpha build. Update check cancelled.");
            }
            updateNeeded = false;
        } else {
            try {
                URLConnection conn = this.url.openConnection();
                conn.setConnectTimeout(5000);

                conn.addRequestProperty("User-Agent", "StBukkitLib (plugin: " + plugin.getName() + ")");

                conn.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = reader.readLine();

                JSONArray array = (JSONArray) JSONValue.parse(response);

                if (array.size() == 0) {
                    if (log) plugin.getLogger().warning("Unable to find any files.");
                    return false;
                }

                this.newVersion = (String) ((JSONObject) array.get(array.size() - 1)).get("name");
                this.versionLink = (String) ((JSONObject) array.get(array.size() - 1)).get("downloadUrl");
                updateNeeded = !curVersion.equals(newVersion);
                if (updateNeeded && log) {
                    logger.log(Level.INFO, "Found a different version on BukkitDev! (Remote: " + newVersion + " | Current: " + curVersion + ")");
                    logger.log(Level.INFO, "You can download it from: " + versionLink);
                }
            } catch (IOException e) {
                if (log) {
                    plugin.getLogger().warning("Unable to check for updates (" + e.getClass().getName() + ")");
                    e.printStackTrace();
                }
                updateNeeded = false;
            }
        }
        return updateNeeded;
    }

}