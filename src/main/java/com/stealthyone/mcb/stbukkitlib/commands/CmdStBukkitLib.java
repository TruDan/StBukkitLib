package com.stealthyone.mcb.stbukkitlib.commands;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.updates.UpdateChecker;
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
        cmdVersion(sender, command, label, args);
		/*if (args.length > 0) {
			switch (args[0]) {
				default: case "version":
					cmdVersion(sender, command, label, args);
					return true;
			}
		}*/
		return true;
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
		UpdateChecker updateChecker = plugin.getUpdateChecker();
		if (updateChecker.isUpdateNeeded()) {
			String curVer = plugin.getVersion();
			String remVer = updateChecker.getNewVersion();
			sender.sendMessage(ChatColor.RED + "A different version was found on BukkitDev! (Current: " + curVer + " | Remote: " + remVer + ")");
			sender.sendMessage(ChatColor.RED + "You can download it from " + updateChecker.getVersionLink());
		}
	}

}