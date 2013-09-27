package com.stealthyone.mcb.stbukkitlib.lib.backend.autosaving;

import com.stealthyone.mcb.stbukkitlib.lib.autosaving.Autosavable;

public class AutosaveItem {

    private String pluginName, name;
    private Autosavable autosavable;
    private int seconds;

    public AutosaveItem(String pluginName, String name, Autosavable autosavable, int seconds) {
        this.pluginName = pluginName;
        this.name = name;
        this.autosavable = autosavable;
        this.seconds = seconds;
    }

    public final String getPluginName() {
        return pluginName;
    }

    public final String getName() {
        return name;
    }

    public final Autosavable getAutosavable() {
        return autosavable;
    }

    public final int getSeconds() {
        return seconds;
    }

}