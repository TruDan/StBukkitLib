package com.stealthyone.mcb.stbukkitlib.lib.messages;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvMessageManager extends MessageManager {

    public AdvMessageManager(JavaPlugin plugin) {
        super(plugin);
    }

    public AdvMessageManager(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    public String getMessage(String name, String... replacements) {
        String[] nameSplit = name.split(".");
        String message;
        try {
            message = messages.get(nameSplit[0]).get(nameSplit[1]);
        } catch (NullPointerException ex) {
            return ChatColor.RED + "Undefined message '" + name + "'";
        }

        for (String replacement : replacements) {
            String[] split = replacement.split("\\|");
            message = message.replace(split[0], split[1]);
        }
        return message;
    }

}