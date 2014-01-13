package com.stealthyone.mcb.stbukkitlib;

import com.stealthyone.mcb.stbukkitlib.lib.players.PlayerUuidManager;
import com.stealthyone.mcb.stbukkitlib.lib.plugin.LogHelper;
import org.bukkit.plugin.java.JavaPlugin;

public class StBukkitLib extends JavaPlugin {

    private static StBukkitLib instance;

    public static StBukkitLib getInstance() {
        return instance;
    }

    private PlayerUuidManager idManager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        idManager = new PlayerUuidManager(this);

        LogHelper.INFO(this, String.format("%s v%s by %s enabled.", getName(), getDescription().getVersion(), getDescription().getAuthors().toString()));
    }

    @Override
    public void onDisable() {
        LogHelper.INFO(this, String.format("%s v%s by %s disabled.", getName(), getDescription().getVersion(), getDescription().getAuthors().toString()));
    }

    public PlayerUuidManager getIdManager() {
        return idManager;
    }

}