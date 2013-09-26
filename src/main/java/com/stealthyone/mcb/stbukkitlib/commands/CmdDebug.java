package com.stealthyone.mcb.stbukkitlib.commands;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffectType;

public class CmdDebug implements CommandExecutor {

    private StBukkitLib plugin;

    public CmdDebug(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "potions":
                cmdPotions(sender, command, label, args);
                return true;
        }
        return true;
    }

    /**
     * Handler for list potions command
     * @param sender
     * @param command
     * @param label
     * @param args
     */
    private final void cmdPotions(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder message = new StringBuilder();
        for (PotionEffectType type : PotionEffectType.values()) {
            if (message.length() > 0) {
                message.append(", ");
            }
            if (type != null) {
                message.append(type.getName());
            }
        }
        sender.sendMessage(message.toString());
    }

}