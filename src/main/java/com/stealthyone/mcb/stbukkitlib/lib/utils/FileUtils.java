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

import java.io.*;

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

    /**
     * Copies a file from the plugin .jar to a destination folder in the plugin datafolder
     * @param plugin
     * @param fileName
     * @param destDir
     * @throws IOException
     */
    public final static void copyGenericFileFromJar(JavaPlugin plugin, String fileName, String destDir) throws IOException {
        copyGenericFileFromJar(plugin, fileName, plugin.getDataFolder() + File.separator + destDir);
    }

    /**
     * Copies a file from the plugin .jar to a destination folder in the plugin datafolder
     * @param plugin
     * @param fileName
     * @param destDir
     * @return New YamlFileManager of copied file
     * @throws IOException
     */
    public final static YamlFileManager copyGenericFileFromJar(JavaPlugin plugin, String fileName, File destDir) throws IOException {
        YamlFileManager file = new YamlFileManager(destDir + File.separator + fileName);
        InputStream in = plugin.getResource(fileName);

        OutputStream out = new FileOutputStream(file.getFile());
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        in.close();
        return file;
    }

}