package com.stealthyone.mcb.stbukkitlib.lib.updates;

import com.stealthyone.mcb.stbukkitlib.lib.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UpdateChecker {

    public final static UpdateChecker scheduleForMe(JavaPlugin plugin, String updateUrl) {
        UpdateChecker checker = new UpdateChecker(plugin, updateUrl);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, checker.getRunnable(), 40, 432000);
        return checker;
    }

    private JavaPlugin plugin;

    private UpdateCheckRunnable runnable;
    private Logger logger;
    private String updateUrl = "somelink";
    private boolean updateNeeded = false;
    private String newVersion = "", versionLink = "";

    public UpdateChecker(JavaPlugin plugin, String url) {
        this.plugin = plugin;
        this.updateUrl = url;
        this.logger = plugin.getLogger();
        runnable = new UpdateCheckRunnable(plugin, updateUrl, logger, this);
    }

    public final UpdateCheckRunnable getRunnable() {
        return runnable;
    }

    public final boolean isUpdateNeeded() {
        return updateNeeded;
    }

    public final String getNewVersion() {
        return newVersion;
    }

    public final String getVersionLink() {
        return versionLink;
    }

    public final boolean checkForUpdates() {
        return checkForUpdates(true);
    }

    public final boolean checkForUpdates(boolean log) {
        try {
            String updateLink = updateUrl;
            String curVersion = plugin.getDescription().getVersion();
            if (!plugin.getConfig().getBoolean("Check for updates", false)) {
                if (log) {
                    logger.log(Level.INFO, "Update checker disabled. Enable it in the config to be alerted of new version of the plugin.");
                }
            } else if (updateLink.equalsIgnoreCase("somelink") || StringUtils.containsMultiple(curVersion, "SNAPSHOT", "BETA", "ALPHA")) {
                if (log) {
                    logger.log(Level.INFO, "Currently running a snapshot, beta, or alpha build. Update check cancelled.");
                }
                updateNeeded = false;
            } else {
                URL filesFeed = new URL(updateLink);
                URLConnection connection = filesFeed.openConnection();
                connection.setConnectTimeout(30000);
                InputStream input = connection.getInputStream();
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);

                Node latestFile = document.getElementsByTagName("item").item(0);
                NodeList children = latestFile.getChildNodes();

                newVersion = children.item(1).getTextContent().replace("v", "");
                versionLink = children.item(3).getTextContent();

                updateNeeded = !curVersion.equals(newVersion);
                return updateNeeded;
            }
        } catch (Exception e) {
            if (log) {
                logger.log(Level.INFO, "Unable to check for updates! (" + e.getClass().getName() + ")");
            }
        }
        return false;
    }

}