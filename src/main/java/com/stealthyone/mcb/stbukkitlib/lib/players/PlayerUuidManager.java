package com.stealthyone.mcb.stbukkitlib.lib.players;

import com.stealthyone.mcb.stbukkitlib.StBukkitLib;
import com.stealthyone.mcb.stbukkitlib.lib.plugin.LogHelper;
import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PlayerUuidManager {

    private StBukkitLib plugin;

    private YamlFileManager idFile;
    private Map<String, String> namesToIds = new HashMap<>();
    private Map<String, String> idsToNames = new HashMap<>();

    public PlayerUuidManager(StBukkitLib plugin) {
        this.plugin = plugin;
        idFile = new YamlFileManager(plugin.getDataFolder() + File.separator + "playerIds.yml");
        LogHelper.INFO(plugin, "Loaded " + reloadData() + " ID to name mappings.");
    }

    public int reloadData() {
        namesToIds.clear();
        idsToNames.clear();
        idFile.reloadConfig();

        FileConfiguration idConfig = idFile.getConfig();
        for (String key : idConfig.getKeys(false)) {
            String name = idConfig.getString(key);
            if (name == null) {
                LogHelper.WARNING(plugin, "Unable to load NAME from ID: " + key);
                continue;
            }
            idsToNames.put(key, name.toLowerCase());
            idsToNames.put(name.toLowerCase(), key);
            LogHelper.DEBUG(plugin, "Mapped ID: " + key + " to NAME: " + name);
        }
        return idsToNames.size();
    }

    public String getName(String uuid) {
        return idsToNames.get(uuid);
    }

    public String getUuid(String name) {
        return namesToIds.get(name.toLowerCase());
    }

}