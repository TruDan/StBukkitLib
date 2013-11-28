package com.stealthyone.mcb.stbukkitlib.permissions;

import org.bukkit.command.CommandSender;

public enum PermissionNode {

    DEBUG,
    VERIFICATION;

    public final static String PREFIX = "stbukkitlib.";

    private String permission;

    private PermissionNode() {
        this.permission = PermissionNode.PREFIX + this.toString().toLowerCase().replace("_", ".");
    }

    public final boolean isAllowed(CommandSender sender) {
        return sender.hasPermission(permission);
    }

}