package com.stealthyone.mcb.stbukkitlib.lib.help;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.backend.help.HelpBackend;
import com.stealthyone.mcb.stbukkitlib.backend.help.exceptions.HelpNotRegisteredException;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelpAPI {

    private final static HelpBackend getHelpBackend() {
        return StBukkitLib.getInstance().getHelpBackend();
    }

    /**
     * Registers a help manager for a specified plugin
     * @param plugin Plugin to register help manager for
     * @return Null if plugin is already registered
     * @throws InstantiationException Thrown if plugin's jar archive doesn't contain a 'help.yml' file
     */
    public final static HelpManager registerHelp(JavaPlugin plugin) {
        if (!getHelpBackend().registerHelpObject(plugin)) {
            return null;
        } else {
            return getHelpBackend().getHelpManager(plugin);
        }
    }

    public final static void handleHelpCommand(JavaPlugin plugin, CommandSender sender, String label, String baseTopic, String[] args, int customTopicIndex) throws HelpNotRegisteredException {
        HelpManager helpManager = getHelpBackend().getHelpManager(plugin);
        if (helpManager == null) {
            throw new HelpNotRegisteredException();
        } else {
            helpManager.handleHelpCommand(sender, baseTopic, label, args, customTopicIndex);
        }
    }

}