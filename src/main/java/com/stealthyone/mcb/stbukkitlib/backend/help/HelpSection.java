package com.stealthyone.mcb.stbukkitlib.backend.help;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.lib.help.HelpManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class HelpSection {

    private HelpManager helpManager;
    private SectionConfig config;

    private String topic;
    private List<String> messages;

    public HelpSection(HelpManager manager, HelpSection parent, ConfigurationSection config) {
        this.helpManager = manager;

        /* Load config */
        this.config = new SectionConfig(config);

        /* Load topic */
        topic = (parent != null && parent.topic.length() > 0 ? parent.topic + "." : "") + config.getName();
        Log.debug("topic: " + topic);

        /* Load messages */
        messages = config.getStringList("messages");

        /* Load subtopics */
        ConfigurationSection subtopicSec = config instanceof FileConfiguration ? config : config.getConfigurationSection("subtopics");
        if (subtopicSec != null) {
            Log.debug("Subtopic section not null");
            for (String key : subtopicSec.getKeys(false)) {
                Log.debug("key: " + key);
                HelpSection section = new HelpSection(manager, this, subtopicSec.getConfigurationSection(key));
                manager.addHelpSection(section.topic, section);
                messages.add(this.config.topicMessage.replace("{TOPIC}", section.topic).replace("{DESCRIPTION}", section.config.description));
            }
        }
        if (messages.size() == 0) {
            messages.add(this.config.emptyMessage);
        }
    }

    public int getPageCount() {
        int pageCount = 0;
        for (int i = 1; i <= messages.size(); i++) {
            if (i % config.pageItems == 1) pageCount++;
        }
        return pageCount;
    }

    public List<String> constructMessages(CommandSender sender, String label, int page) {
        List<String> returnList = new ArrayList<>();
        String permission = config.permission;
        if (permission != null && !sender.hasPermission(permission)) {
            returnList.add(config.permissionMessage);
        } else {
            for (int i = 0; i < config.pageItems; i++) {
                int index = ((page - 1) * 8) + i;
                try {
                    returnList.add(messages.get(index).replace("{PAGE}", Integer.toString(page)).replace("{PAGECOUNT}", Integer.toString(getPageCount())).replace("{LABEL}", label));
                } catch (IndexOutOfBoundsException ex) {
                    break;
                }
            }
        }
        return returnList;
    }

    public String constructHeader(String topic, int page, int maxPages) {
        return ChatColor.translateAlternateColorCodes('&', config.header.replace("{TOPIC}", topic).replace("{PAGE}", Integer.toString(page)).replace("{PAGECOUNT}", Integer.toString(maxPages)));
    }

    public String constructFooter(String topic, int page, int maxPages) {
        return ChatColor.translateAlternateColorCodes('&', config.footer.replace("{TOPIC}", topic).replace("{PAGE}", Integer.toString(page)).replace("{PAGECOUNT}", Integer.toString(maxPages)));
    }

}