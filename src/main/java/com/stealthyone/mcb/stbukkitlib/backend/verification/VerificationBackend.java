package com.stealthyone.mcb.stbukkitlib.backend.verification;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.StBukkitLib.Log;
import com.stealthyone.mcb.stbukkitlib.lib.verification.Verifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerificationBackend {

    private StBukkitLib plugin;

    private Map<String, List<Verifiable>> loadedVerifiables = new HashMap<String, List<Verifiable>>();

    public VerificationBackend(StBukkitLib plugin) {
        this.plugin = plugin;
    }

    public final boolean registerVerifiable(String playerName, Verifiable verifiable) {
        List<Verifiable> playerList = loadedVerifiables.get(playerName.toLowerCase());
        if (playerList == null) {
            playerList = new ArrayList<Verifiable>();
        } else {
            for (Verifiable ver : playerList) {
                if (ver.getId().equals(verifiable.getId())) {
                    return false;
                }
            }
        }
        Log.debug("Registered verifiable: " + verifiable.getClass().getName());
        playerList.add(verifiable);
        loadedVerifiables.put(playerName.toLowerCase(), playerList);
        return true;
    }

    public final Verifiable getLatestVerifiable(String playerName) {
        List<Verifiable> playerList = loadedVerifiables.get(playerName.toLowerCase());
        return (playerList == null || playerList.size() == 0) ? null : playerList.get(playerList.size() - 1);
    }

    public final List<Verifiable> getVerifiables(String playerName) {
        List<Verifiable> playerList = loadedVerifiables.get(playerName.toLowerCase());
        return (playerList == null || playerList.size() == 0) ? null : playerList;
    }

    public final void removeLatestVerifiable(String playerName) {
        loadedVerifiables.remove(playerName.toLowerCase());
    }

    public final void removeVerifiable(String playerName, int id) {
        List<Verifiable> playerList = loadedVerifiables.get(playerName.toLowerCase());
        if (!(playerList == null || playerList.size() == 0)) {
            try {
                playerList.remove(id);
            } catch (IndexOutOfBoundsException ex) {
            }
        }
    }

}