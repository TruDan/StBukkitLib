/*
 * StBukkitLib - FileUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import com.stealthyone.mcb.stbukkitlib.lib.storage.YamlFileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class FileUtils {

    /**
     * Copies a file from the plugin .jar to the datafolder
     *
     * @param plugin
     * @param fileName
     */
    public final static void copyGenericFileFromJar(JavaPlugin plugin, String fileName) {
        YamlFileManager file = new YamlFileManager(new File(plugin.getDataFolder() + File.separator + fileName));
        InputStream in = plugin.getResource(fileName);

        try {
            OutputStream out = new FileOutputStream(file.getFile());
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}