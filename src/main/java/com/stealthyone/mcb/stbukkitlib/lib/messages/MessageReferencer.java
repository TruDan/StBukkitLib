package com.stealthyone.mcb.stbukkitlib.lib.messages;

import org.bukkit.command.CommandSender;

public interface MessageReferencer {

    public String getMessagePath();
    public void sendTo(CommandSender sender);
    public void sendTo(CommandSender sender, String... replacements);

}