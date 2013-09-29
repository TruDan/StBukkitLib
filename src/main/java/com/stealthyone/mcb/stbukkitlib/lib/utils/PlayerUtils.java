/*
 * StBukkitLib - PlayerUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.stealthyone.mcb.stbukkitlib.lib.permissions.IPermissionNode;
import com.stealthyone.mcb.stbukkitlib.lib.utils.exceptions.NameMatchesMultiplePlayersException;
import com.stealthyone.mcb.stbukkitlib.lib.utils.exceptions.NameMatchesNoPlayers;

public final class PlayerUtils {

    /**
     * Attempts to match a given name with an online player
     *
     * @param name Name of player to getBoolean
     * @return Player that matches name
     * @throws NameMatchesNoPlayers Name matches no online players
     * @throws NameMatchesMultiplePlayersException
     *                              Name matches multiple online players
     */
    public final static Player getPlayer(String name) throws NameMatchesMultiplePlayersException, NameMatchesNoPlayers {
        List<Player> matchedPlayers = Bukkit.matchPlayer(name);

        if (matchedPlayers.size() == 0) {
            throw new NameMatchesNoPlayers();
        } else if (matchedPlayers.size() > 1) {
            throw new NameMatchesMultiplePlayersException();
        } else {
            return Bukkit.getPlayer(name);
        }
    }

    /**
     * Returns a list of players that have at least one of the permissions in the input permissions
     *
     * @param onlinePlayers Input players, should be Bukkit.getOnlinePlayers()
     * @param permissions   List of permissions to check for
     * @return List of players that have one or more of the input permissions
     */
    public final static List<Player> getPlayerListWithPermission(Player[] onlinePlayers, IPermissionNode... permissions) {
        List<Player> returnList = new ArrayList<Player>();

        for (Player player : onlinePlayers) {
            for (IPermissionNode perm : permissions) {
                if (player.hasPermission(perm.get())) {
                    if (!returnList.contains(player))
                        returnList.add(player);
                }
            }
        }

        return returnList;
    }

    /**
     * Returns a list of nearby players to a target player
     *
     * @param target Main player
     * @param radius Radius to check in
     * @return Player array of nearby players
     */
    public final static List<Player> getNearbyPlayers(Player target, int radius) {
        List<Player> playerList = new ArrayList<Player>();
        for (Entity entity : target.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Player) {
                playerList.add((Player) entity);
            }
        }
        return playerList;
    }

    /**
     * Returns the distance between two players
     *
     * @param player
     * @param otherPlayer
     * @return Null if players are in different worlds
     */
    public final static double distanceFrom(Player player, Player otherPlayer) {
        Location pos1 = player.getLocation();
        Location pos2 = otherPlayer.getLocation();
        if (pos1.getWorld() != pos2.getWorld()) {
            return Double.NaN;
        }
        return pos1.distance(pos2);
    }
}