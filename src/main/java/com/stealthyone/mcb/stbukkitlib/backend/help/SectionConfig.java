package com.stealthyone.mcb.stbukkitlib.backend.help;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class SectionConfig {

    String description = "&c&oNo description set.";
    String permission = "";
    String permissionMessage = "&c&oNo permission for this section.";
    String emptyMessage = "&c&oNothing here.";
    String topicMessage = "&cTopic: &6{TOPIC}&8: &3{DESCRIPTION}";
    String header = "&8=====&aHelp: &2{TOPIC}&8=====";
    String footer = "&8=====&aPage {PAGE}/{PAGECOUNT}&8=====";
    int pageItems = 8;

    public SectionConfig(ConfigurationSection config) {
        if (config == null || config instanceof FileConfiguration || config.getConfigurationSection("config") == null) return;
        config = config.getConfigurationSection("config");
        Log.debug("keys: " + config.getKeys(false).toString());
        description = config.getString("description", description);
        permission = config.getString("permission", permission);
        permissionMessage = config.getString("permissionMessage", permissionMessage);
        emptyMessage = config.getString("emptyMessage", emptyMessage);
        topicMessage = config.getString("topicMessage", topicMessage);
        header = config.getString("header", header);
        footer = config.getString("footer", footer);
        pageItems = config.getInt("pageItems", pageItems);
    }

}