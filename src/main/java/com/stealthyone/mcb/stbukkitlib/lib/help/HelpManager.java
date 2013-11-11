package com.stealthyone.mcb.stbukkitlib.lib.help;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.backend.help.HelpBackend;
import com.stealthyone.mcb.stbukkitlib.backend.help.HelpSection;
import com.stealthyone.mcb.stbukkitlib.lib.storage.JarYamlFileManager;
import com.stealthyone.mcb.stbukkitlib.lib.utils.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class HelpManager {

    private String pluginName;

    private JarYamlFileManager helpFile;
    private Map<String, HelpSection> helpSections = new HashMap<String, HelpSection>();

    public HelpManager(JavaPlugin plugin) throws FileNotFoundException {
        this.pluginName = plugin.getName();
        helpFile = new JarYamlFileManager(plugin, "help.yml");
        Log.info("Loaded " + reloadMessages() + " help topics in help.yml for plugin: " + pluginName);
    }

    public JarYamlFileManager getHelpFile() {
        return helpFile;
    }

    public int reloadMessages() {
        helpSections.clear();
        loadSection(helpFile.getConfig().getCurrentPath());
        return helpSections.size();
    }

    private void loadSection(String path) {
        Object object = helpFile.getConfig().get(path);
        if (object instanceof ConfigurationSection) {
            ConfigurationSection configSection = (ConfigurationSection) object;
            HelpSection helpSection = new HelpSection(this, configSection);
            for (String key : configSection.getKeys(false)) {
                loadSection((!path.equalsIgnoreCase("") ? path + "." : "") + key);
            }
            helpSections.put(!path.equalsIgnoreCase("") ? path : "(none)", helpSection);
        }/* else {
            Log.severe("Invalid help value in config for plugin: '" + pluginName + "': " + path);
        }*/
    }

    public JavaPlugin getPlugin() {
        return (JavaPlugin) Bukkit.getPluginManager().getPlugin(pluginName);
    }

    public HelpSection getHelpSection(String topic) {
        return helpSections.get(topic.toLowerCase());
    }

    public void handleHelpCommand(CommandSender sender, String baseTopic, String label, String[] args, int customTopicIndex) {
        JavaPlugin plugin = getPlugin();
        HelpBackend helpBackend = StBukkitLib.getInstance().getHelpBackend();
        HelpManager helpManager = helpBackend.getHelpManager(plugin);
        String customTopic;
        try {
            String temp = args[customTopicIndex];
            try {
                Integer.parseInt(temp);
                customTopic = null;
            } catch (NumberFormatException ex) {
                customTopic = args[customTopicIndex];
            }
        } catch (IndexOutOfBoundsException ex) {
            customTopic = null;
        }

        StringBuilder finalTopic = new StringBuilder();
        if (baseTopic == null && customTopic == null) {
            finalTopic.append("(none)");
        } else if (baseTopic != null && customTopic == null) {
            finalTopic.append(baseTopic);
        } else if (baseTopic == null) {
            finalTopic.append(customTopic);
        } else {
            finalTopic.append(baseTopic).append(".").append(customTopic);
        }
        String finalTopicString = finalTopic.toString();
        String friendlyTopic = plugin.getName() + (!finalTopicString.equalsIgnoreCase("(none)") ? "/" + finalTopicString.replace(".", "/") : "");

        boolean lastCharNum = false;
        int pageNum = 1;
        try {
            pageNum = Integer.parseInt(args[args.length - 1]);
            lastCharNum = true;
        } catch (IndexOutOfBoundsException ex) {
            pageNum = 1;
        } catch (NumberFormatException ex) {
            if (customTopicIndex < args.length) {
                handleHelpCommand(sender, finalTopicString, label, args, customTopicIndex + 1);
                return;
            }
        }

        sender.sendMessage(ChatColor.DARK_GRAY + "=====" + ChatColor.GREEN + "Help: " + ChatColor.GOLD + friendlyTopic + ChatColor.GREEN + " (pg." + pageNum + ")" + ChatColor.DARK_GRAY + "=====");
        HelpSection section = helpManager.getHelpSection(finalTopic.toString());
        if (section == null) {
            sender.sendMessage("" + ChatColor.RED + ChatColor.ITALIC + "Invalid section");
        } else {
            for (String message : section.getMessages(pageNum)) {
                sender.sendMessage(message);
            }

            int pageCount = section.getPageCount();
            String command = "/" + label + " " + ArrayUtils.stringArrayToString(args, 0, lastCharNum ? args.length - 1 : args.length);
            if (section.hasSubTopics() && pageNum <= pageCount) {
                sender.sendMessage(ChatColor.DARK_RED + "Type " + ChatColor.RED + command + " <topic> [page]" + ChatColor.DARK_RED + " to access another topic.");
            }

            if (pageNum < pageCount) {
                sender.sendMessage(ChatColor.DARK_RED + "Type " + ChatColor.RED + command + " " + (pageNum + 1) + ChatColor.DARK_RED + " to access the next page.");
            }

        }
    }

}