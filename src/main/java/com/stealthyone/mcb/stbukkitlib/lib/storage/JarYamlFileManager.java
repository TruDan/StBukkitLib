/*
 * StBukkitLib - JarYamlFileManager
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.storage;

import com.stealthyone.mcb.stbukkitlib.lib.utils.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class intended for retrieving configuration of files located in the plugin .jar itself.
 *
 * @author Stealth2800
 */
public class JarYamlFileManager {

    private JavaPlugin plugin;
    private String fileName;
    private FileConfiguration config;

    /**
     * Constructs a new JarYamlFileManager using a specified file name.
     *
     * @param plugin
     * @param fileName Name of file. Include the extension as well as the name (typically .yml).
     * @throws FileNotFoundException Thrown if file doesn't exist in the .jar
     */
    public JarYamlFileManager(JavaPlugin plugin, String fileName) throws FileNotFoundException {
        this.plugin = plugin;
        this.fileName = fileName;
        InputStream stream = plugin.getResource(fileName);
        if (stream != null) {
            config = YamlConfiguration.loadConfiguration(stream);
        } else {
            throw new FileNotFoundException("File '" + fileName + "' doesn't exist in plugin jar archive");
        }
    }

    /**
     * Returns the configuration of the file located in the .jar.
     *
     * @return FileConfiguration of file.
     */
    public final FileConfiguration getConfig() {
        return config;
    }

    public final void copyTo(String path) throws IOException {
        FileUtils.copyGenericFileFromJar(plugin, fileName, path);
    }

    /**
     * Copies file to specified directory
     * @param dir Directory to copy to
     * @throws IOException Thrown if file doesn't exist in jar archive
     */
    public final YamlFileManager copyTo(File dir) throws IOException {
        return FileUtils.copyGenericFileFromJar(plugin, fileName, dir);
    }

}