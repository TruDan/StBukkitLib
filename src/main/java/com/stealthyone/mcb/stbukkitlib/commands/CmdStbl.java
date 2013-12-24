package com.stealthyone.mcb.stbukkitlib.commands;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdStbl implements CommandExecutor {

    private StBukkitLib plugin;

    public CmdStbl(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            switch (args[0].toLowerCase()) {
                case "help":
                    plugin.getHelpManager().handleHelpCommand(sender, label, args, null, 1);
                    return true;

                default:
                    break;
            }
        }
        cmdVersion(sender, command, label, args);
        return true;
    }

    private void cmdVersion(CommandSender sender, Command command, String label, String[] args) {

    }

}