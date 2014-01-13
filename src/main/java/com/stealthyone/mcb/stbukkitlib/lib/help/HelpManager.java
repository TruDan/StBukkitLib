package com.stealthyone.mcb.stbukkitlib.lib.help;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.backend.help.HelpSection;
import com.stealthyone.mcb.stbukkitlib.lib.storage.JarYamlFileManager;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class HelpManager {

    private JavaPlugin plugin;

    private JarYamlFileManager helpFile;
    private Map<String, HelpSection> helpSections = new HashMap<>();

    public HelpManager(JavaPlugin plugin) {
        this(plugin, "help.yml");
    }

    public HelpManager(JavaPlugin plugin, String fileName) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(fileName, "File name cannot be null");

        this.plugin = plugin;
        try {
            helpFile = new JarYamlFileManager(plugin, "help.yml");
        } catch (FileNotFoundException ex) {
            Log.severe("Unable to load help for plugin: " + plugin.getName());
            ex.printStackTrace();
            return;
        }
        reloadHelp();
        Log.debug("Plugin: " + plugin.getName() + " help sections -> " + helpSections.keySet().toString());
        Log.info("Help loaded for plugin: " + plugin.getName());
    }

    public void reloadHelp() {
        helpSections.clear();
        addHelpSection(null, new HelpSection(this, null, helpFile.getConfig()));
    }

    public void addHelpSection(String topic, HelpSection section) {
        if (topic == null) topic = "(none)";

        helpSections.put(topic.toLowerCase(), section);
    }

    public void showStaticHelp(CommandSender sender, String label, String topic) {
        topic = topic != null ? topic : "(none)";
        HelpSection section = helpSections.get(topic.toLowerCase());
        if (section == null) {
            sender.sendMessage(ChatColor.RED + "Invalid help topic: " + topic);
        } else {
            for (String string : section.constructMessages(sender, label, 1)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
            }
        }
    }

    public void handleHelpCommand(CommandSender sender, String label, String[] args, String baseTopic, int startIndex) {
        Log.debug("Handle help command for " + sender.getName() + ", label: " + label + ", baseTopic: " + baseTopic + ", startIndex: " + startIndex);

        int page = 1;
        /* Get customTopic */
        String customTopic = null;
        try {
            String temp = args[startIndex];
            try {
                page = Integer.parseInt(temp);
            } catch (NumberFormatException ex) {
                customTopic = args[startIndex];
            }
        } catch (IndexOutOfBoundsException ex) {}
        Log.debug("customTopic: " + customTopic);

        /* Create finalTopic */
        String finalTopic;
        if (baseTopic == null && customTopic == null) {
            finalTopic = "(none)";
        } else if (baseTopic != null && customTopic == null) {
            finalTopic = baseTopic;
        } else if (baseTopic == null) {
            finalTopic = customTopic;
        } else {
            finalTopic = baseTopic + "." + customTopic;
        }
        Log.debug("finalTopic: " + finalTopic);

        /* Get page */
        try {
            page = Integer.parseInt(args[startIndex + 1]);
        } catch (IndexOutOfBoundsException ex) {
        } catch (NumberFormatException ex) {
            if (startIndex < args.length) {
                handleHelpCommand(sender, label, args, finalTopic, startIndex + 1);
                return;
            }
        }
        Log.debug("page: " + page);

        HelpSection section = helpSections.get(finalTopic.toLowerCase());
        if (section == null) {
            sender.sendMessage(ChatColor.RED + "Invalid help topic: " + ChatColor.DARK_RED + finalTopic);
        } else {
            int pageCount = section.getPageCount();
            if (page <= 0 || page > pageCount) {
                sender.sendMessage(ChatColor.RED + "Invalid page");
                return;
            }

            sender.sendMessage(section.constructHeader(finalTopic.equalsIgnoreCase("(none)") ? plugin.getName() : finalTopic, page, pageCount));
            for (String string : section.constructMessages(sender, label, page)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
            }
            sender.sendMessage(section.constructFooter(finalTopic, page, pageCount));
        }
    }

}