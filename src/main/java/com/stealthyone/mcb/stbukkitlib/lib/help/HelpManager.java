package com.stealthyone.mcb.stbukkitlib.lib.help;

import com.stealthyone.mcb.stbukkitlib.backend.help.HelpSection;
import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import com.stealthyone.mcb.stbukkitlib.lib.utils.FileUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelpManager {

    private JavaPlugin plugin;

    private YamlFileManager helpFile;
    private Map<String, HelpSection> helpSections = new HashMap<>();

    public HelpManager(JavaPlugin plugin) {
        this(plugin, "help.yml");
    }

    public HelpManager(JavaPlugin plugin, String fileName) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(fileName, "File name cannot be null");

        this.plugin = plugin;
        helpFile = new YamlFileManager(plugin.getDataFolder() + File.separator + "help.yml");
        if (helpFile.isEmpty()) {
            try {
                FileUtils.copyFileFromJar(plugin, "help.yml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            helpFile.reloadConfig();
        }
        reloadHelp();
    }

    public void reloadHelp() {
        helpSections.clear();
        helpFile.reloadConfig();
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
        String finalTopic = baseTopic != null ? baseTopic : "";
        try {
            finalTopic = finalTopic + "." + args[startIndex];
        } catch (IndexOutOfBoundsException ex) {
            if (finalTopic.length() == 0) finalTopic = "(none)";
        }

        int page = 1;
        try {
            page = Integer.parseInt(args[args.length - 1]);
        } catch (IndexOutOfBoundsException ex) {

        } catch (NumberFormatException ex) {
            if (startIndex < args.length) {
                handleHelpCommand(sender, label, args, finalTopic, startIndex + 1);
                return;
            }
        }

        HelpSection section = helpSections.get(finalTopic.toLowerCase());
        if (section == null) {
            sender.sendMessage(ChatColor.RED + "Invalid help topic: " + finalTopic);
        } else {
            int pageCount = section.getPageCount();

            sender.sendMessage(section.constructHeader(finalTopic, page, pageCount));
            for (String string : section.constructMessages(sender, label, page)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
            }
            sender.sendMessage(section.constructFooter(finalTopic, page, pageCount));
        }
    }

}