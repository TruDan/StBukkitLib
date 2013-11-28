package com.stealthyone.mcb.stbukkitlib.commands;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import com.stealthyone.mcb.stbukkitlib.messages.ErrorMessage;
import com.stealthyone.mcb.stbukkitlib.permissions.PermissionNode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.Date;

public class CmdDebug implements CommandExecutor {

    private StBukkitLib plugin;

    public CmdDebug(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!PermissionNode.DEBUG.isAllowed(sender)) {
            ErrorMessage.NO_PERMISSION.sendTo(sender);
            return true;
        }

        switch (args[0]) {
            case "potions":
                cmdPotions(sender, command, label, args);
                return true;

            case "saveinv":
                cmdSaveInv(sender, command, label, args);
                return true;
        }
        return true;
    }

    /**
     * Handler for list potions command
     *
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

    /**
     * Handler for save inventory command
     * @param sender
     * @param command
     * @param label
     * @param args
     */
    private void cmdSaveInv(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("must be player");
            return;
        }
        String fileName = "inv_" + sender.getName() + "-" + new Date().getTime() + ".yml";
        YamlFileManager file = new YamlFileManager(plugin.getDataFolder() + File.separator + fileName);
        file.getConfig().set("inventory", ((Player) sender).getInventory().getContents());
        file.saveFile();
        sender.sendMessage("saved inventory to " + fileName);
    }

}