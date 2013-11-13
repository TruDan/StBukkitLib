package com.stealthyone.mcb.stbukkitlib.commands;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.messages.ErrorMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class CmdStBukkitLib implements CommandExecutor {

    private StBukkitLib plugin;

    public CmdStBukkitLib(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
			switch (args[0]) {
                case "help":
                    cmdHelp(sender, command, label, args);
                    return true;

				default:
                    ErrorMessage.UNKNOWN_COMMAND.sendTo(sender);
                    return true;

                case "version":
					cmdVersion(sender, command, label, args);
					return true;
			}
		}
        cmdVersion(sender, command, label, args);
        return true;
    }

    /**
     * Handler for help command
     * @param sender
     * @param command
     * @param label
     * @param args
     */
    private final void cmdHelp(CommandSender sender, Command command, String label, String[] args) {
        plugin.getHelpManager().handleHelpCommand(sender, null, label, args, 1);
    }

    /**
     * Command handler for version command
     * @param sender
     * @param command
     * @param label
     * @param args
     */
    private final void cmdVersion(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + plugin.getName() + ChatColor.GOLD + " v" + plugin.getVersion());
        sender.sendMessage(ChatColor.GOLD + "Created by " + plugin.getDescription().getAuthors());
        sender.sendMessage(ChatColor.GOLD + "Website: " + ChatColor.AQUA + plugin.getDescription().getWebsite());
        /*Updater updateChecker = plugin.getUpdater();
        if (updateChecker.getResult() == UpdateResult.UPDATE_AVAILABLE) {
            String curVer = plugin.getVersion();
            String remVer = updateChecker.getLatestName().replace("v", "");
            sender.sendMessage(ChatColor.RED + "A different version was found on BukkitDev! (Current: " + curVer + " | Remote: " + remVer + ")");
            sender.sendMessage(ChatColor.RED + "You can download it from " + updateChecker.getLatestFileLink());
        }*/
    }

}