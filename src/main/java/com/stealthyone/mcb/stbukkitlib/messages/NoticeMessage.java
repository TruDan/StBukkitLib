package com.stealthyone.mcb.stbukkitlib.messages;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.messages.IMessagePath;
import com.stealthyone.mcb.stbukkitlib.lib.messages.MessageRetriever;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum NoticeMessage implements IMessagePath {

    MULTIPLE_VERIFICATIONS_PENDING;

	private final String PREFIX = "messages.notices.";
	
	private String path;
	private boolean isList;
	
	private NoticeMessage() {
		this(false);
	}
	
	private NoticeMessage(boolean isList) {
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
	
	public final void sendTo(CommandSender sender) {
		MessageRetriever messageRetriever = StBukkitLib.getInstance().getMessageManager();
		String[] messages = messageRetriever.getMessage(this);
		
		for (String message : messages) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{TAG}", ChatColor.GOLD + "[" + StBukkitLib.getInstance().getName() + "] ")));
		}
	}
	
	public final void sendTo(CommandSender sender, String... replacements) {
		MessageRetriever messageRetriever = StBukkitLib.getInstance().getMessageManager();
		String[] messages = messageRetriever.getMessage(this);
		
		for (String message : messages) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format(message.replace("{TAG}", ChatColor.GOLD + "[" + StBukkitLib.getInstance().getName() + "] "), (Object[]) replacements)));
		}
	}
	
}