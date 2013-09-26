package com.stealthyone.mcb.stbukkitlib.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum UsageMessage {

	VERIFY("/<yes|no> <id>");
	
	private String message;
	
	private UsageMessage(String message) {
		this.message = message;
	}
	
	public final void sendTo(CommandSender sender, String label) {
		sender.sendMessage(ChatColor.DARK_RED + "USAGE: " + ChatColor.RED + message.replace("{LABEL}", label));
	}
	
	public final void sendTo(CommandSender sender, String label, String... replacements) {
		sender.sendMessage(ChatColor.DARK_RED + "USAGE: " + ChatColor.RED + String.format(message.replace("{LABEL}", label), (Object[]) replacements));
	}
	
}