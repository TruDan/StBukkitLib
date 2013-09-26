package com.stealthyone.mcb.stbukkitlib.commands;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.verification.Verifiable;
import com.stealthyone.mcb.stbukkitlib.messages.ErrorMessage;
import com.stealthyone.mcb.stbukkitlib.messages.UsageMessage;
import com.stealthyone.mcb.stbukkitlib.verification.VerificationManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CmdVerify implements CommandExecutor {

    private StBukkitLib plugin;

    public CmdVerify(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String playerName = sender.getName().toLowerCase();
        VerificationManager verificationManager = plugin.getVerificationManager();
        List<Verifiable> playerList = verificationManager.getVerifiables(playerName);
        if (playerList == null || playerList.size() == 0) {
            ErrorMessage.NO_PENDING_VERIFICATION.sendTo(sender);
        } else if (playerList.size() == 1) {
            Verifiable verifiable = verificationManager.getLatestVerifiable(playerName);
            if (label.equalsIgnoreCase("yes")) {
                verifiable.yes();
            } else if (label.equalsIgnoreCase("no")) {
                verifiable.no();
            }
            verificationManager.removeLatestVerifiable(playerName);
        } else if (args.length < 1) {
            ErrorMessage.PENDING_VERIFICATION_OF_SAME_TYPE.sendTo(sender);
            UsageMessage.VERIFY.sendTo(sender, label);
            sender.sendMessage(ChatColor.GRAY + "======== " + ChatColor.GOLD + "Questions to Verify" + ChatColor.GRAY + " ========");
            for (int i = 0; i < playerList.size(); i++) {
                sender.sendMessage("" + ChatColor.YELLOW + (i + 1) + ") " + playerList.get(i).getDesc());
            }
        } else {
            int id;
            try {
                id = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException ex) {
                ErrorMessage.ID_MUST_BE_INT.sendTo(sender);
                return true;
            }

            Verifiable verifiable;
            try {
                verifiable = playerList.get(id);
            } catch (IndexOutOfBoundsException ex) {
                ErrorMessage.ID_INVALID.sendTo(sender);
                return true;
            }

            if (label.equalsIgnoreCase("yes")) {
                verifiable.yes();
            } else if (label.equalsIgnoreCase("no")) {
                verifiable.no();
            }
            verificationManager.removeVerifiable(playerName, id);
        }
        return true;
    }

}