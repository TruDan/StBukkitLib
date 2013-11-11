package com.stealthyone.mcb.stbukkitlib.lib.verification;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.messages.ErrorMessage;
import com.stealthyone.mcb.stbukkitlib.backend.verification.VerificationBackend;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public final class VerificationAPI {

    private final static VerificationBackend getVerificationManager() {
        return StBukkitLib.getInstance().getVerificationBackend();
    }

    /**
     * Registers a verifiable for a player
     *
     * @param playerName
     * @param verifiable
     * @return True if successful, false if player already has pending verifiable
     */
    public final static void registerVerifiable(String playerName, Verifiable verifiable) {
        if (!getVerificationManager().registerVerifiable(playerName, verifiable)) {
            if (playerName.equalsIgnoreCase("console")) {
                ErrorMessage.PENDING_VERIFICATION_OF_SAME_TYPE.sendTo(Bukkit.getConsoleSender());
            } else {
                OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
                if (player.isOnline()) {
                    ErrorMessage.PENDING_VERIFICATION_OF_SAME_TYPE.sendTo(player.getPlayer());
                }
            }
        } else if (Bukkit.getOfflinePlayer(playerName).isOnline()) {
            Bukkit.getOfflinePlayer(playerName).getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', verifiable.getQuestion() + ChatColor.DARK_RED + " /yes" + ChatColor.RED + " or " + ChatColor.DARK_RED + "/no"));
        }
    }

    /**
     * Returns the latest verifiable for a player
     *
     * @param playerName
     * @return Null if player doesn't have any verifiables
     */
    public final static Verifiable getLatestVerifiable(String playerName) {
        return getVerificationManager().getLatestVerifiable(playerName);
    }

}