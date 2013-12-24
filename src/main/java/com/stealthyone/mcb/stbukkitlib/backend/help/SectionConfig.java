package com.stealthyone.mcb.stbukkitlib.backend.help;

import org.bukkit.configuration.ConfigurationSection;

public class SectionConfig {

    String description = "&c&oNo description set.";
    String permission = "";
    String permissionMessage = "&c&oNo permission for this section.";
    String emptyMessage = "&c&oNothing here.";
    String topicMessage = "&cTopic: &6{PATH}&8: &3{DESCRIPTION}";
    String header = "&8=====&aHelp: &2{TOPIC}&8=====";
    String footer = "&8=====&aPage {PAGE}/{PAGECOUNT}&8=====";
    int pageItems = 8;

    public SectionConfig(ConfigurationSection config) {
        description = config.getString("description", description);
        permission = config.getString("permission", permission);
        permissionMessage = config.getString("permisionMessage", permissionMessage);
        emptyMessage = config.getString("emptyMessage", emptyMessage);
        topicMessage = config.getString("topicMessage", topicMessage);
        header = config.getString("header", header);
        footer = config.getString("footer", footer);
        pageItems = config.getInt("pageItems", pageItems);
    }

}