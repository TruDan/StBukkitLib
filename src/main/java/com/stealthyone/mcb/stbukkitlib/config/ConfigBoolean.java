package com.stealthyone.mcb.stbukkitlib.config;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;

public enum ConfigBoolean {

    CHECK_FOR_UPDATES("Check for updates"),
    DEBUG("Debug"),
    DISABLE_VERIFICATION("Disabled components.Verification API");

    private String path;

    private ConfigBoolean(String path) {
        this.path = path;
    }

    public final boolean getBoolean() {
        return StBukkitLib.getInstance().getConfig().getBoolean(path);
    }

}