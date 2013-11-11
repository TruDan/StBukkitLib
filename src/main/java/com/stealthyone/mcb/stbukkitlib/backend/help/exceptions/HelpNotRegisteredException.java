package com.stealthyone.mcb.stbukkitlib.backend.help.exceptions;

import org.bukkit.ChatColor;

public class HelpNotRegisteredException extends Exception {

    public String getErrorMessage() {
        return ChatColor.RED + "Help not registered";
    }

}