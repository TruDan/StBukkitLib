package com.stealthyone.mcb.stbukkitlib.messages;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.messages.IMessagePath;
import com.stealthyone.mcb.stbukkitlib.lib.messages.MessageRetriever;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum ErrorMessage implements IMessagePath {

    ID_INVALID,
    ID_MUST_BE_INT,
    PENDING_VERIFICATION_OF_SAME_TYPE,
	NO_PENDING_VERIFICATION;
	
	private final String PREFIX = "messages.errors.";
	
	private String path;
	private boolean isList;
	
	private ErrorMessage() {
		this(false);
	}
	
	private ErrorMessage(boolean isList) {
		this.path = this.toString().toLowerCase();
		this.isList = isList;
	}
	
	@Override
	public final String getPrefix() {
		return PREFIX;
	}

	@Override
	public final String getMessagePath() {
		return this.path;
	}
	
	@Override
	public final boolean isList() {
		return this.isList;
	}
	
	public final String getFirstLine() {
		return StBukkitLib.getInstance().getMessageManager().getMessage(this)[0];
	}
	
	public final void sendTo(CommandSender sender) {
		MessageRetriever messageRetriever = StBukkitLib.getInstance().getMessageManager();
		String[] messages = messageRetriever.getMessage(this);
		
		for (String message : messages) {
			message = ChatColor.RED + message;
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{TAG}", ChatColor.GOLD + "[" + StBukkitLib.getInstance().getName() + "] " + ChatColor.RED)));
		}
	}
	
	public final void sendTo(CommandSender sender, String... replacements) {
		MessageRetriever messageRetriever = StBukkitLib.getInstance().getMessageManager();
		String[] messages = messageRetriever.getMessage(this);
		
		for (String message : messages) {
			message = ChatColor.RED + message;
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.RED + String.format(message.replace("{TAG}", ChatColor.GOLD + "[" + StBukkitLib.getInstance().getName() + "] " + ChatColor.RED), (Object[]) replacements)));
		}
	}
	
}