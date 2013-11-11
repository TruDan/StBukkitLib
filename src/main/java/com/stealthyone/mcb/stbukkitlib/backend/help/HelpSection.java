package com.stealthyone.mcb.stbukkitlib.backend.help;

import com.stealthyone.mcb.stbukkitlib.lib.help.HelpManager;
import com.stealthyone.mcb.stbukkitlib.lib.storage.JarYamlFileManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class HelpSection {

    private HelpManager helpManager;
    private ConfigurationSection config;

    private List<String> messages;
    private boolean hasSubTopics = false;

    public HelpSection(HelpManager helpIndexer, ConfigurationSection config) {
        this.helpManager = helpIndexer;
        this.config = config;
        reloadMessages();
    }

    private JarYamlFileManager getHelpFile() {
        return helpManager.getHelpFile();
    }

    public String getDescription() {
        return config.getString("description", "" + ChatColor.RED + ChatColor.ITALIC + "Not set");
    }

    public boolean hasSubTopics() {
        return hasSubTopics;
    }

    public void reloadMessages() {
        List<String> newMessages = config.getStringList("messages");

        for (String key : config.getKeys(false)) {
            if (!key.equalsIgnoreCase("description") && !key.equalsIgnoreCase("messages")) {
                newMessages.add(ChatColor.RED + "Topic: " + ChatColor.GOLD + key + ChatColor.DARK_GRAY + " - " + ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', config.getString(key + ".description", "" + ChatColor.RED + ChatColor.ITALIC + "No description available")));
                hasSubTopics = true;
            }
        }
        if (newMessages.size() == 0) newMessages.add("" + ChatColor.RED + ChatColor.ITALIC + "Nothing here");

        messages = newMessages;
    }

    public List<String> getMessages(int page) {
        List<String> returnList = new ArrayList<String>();
        int startIndex = (page - 1) * 8;
        for (int i = 0; i < 8; i++) {
            int curIndex = startIndex + i;
            try {
                returnList.add(ChatColor.translateAlternateColorCodes('&', messages.get(curIndex)));
            } catch (IndexOutOfBoundsException ex) {
                if (returnList.size() == 0) {
                    returnList.add("" + ChatColor.RED + ChatColor.ITALIC + "Nothing here");
                }
            }
        }
        return returnList;
    }

    public int getPageCount() {
        int pageCount = 0;
        int messageCount = messages.size();
        for (int i = 1; i <= messageCount; i++) {
            if (i % 8 == 1) pageCount++;
        }
        return pageCount;
    }

}