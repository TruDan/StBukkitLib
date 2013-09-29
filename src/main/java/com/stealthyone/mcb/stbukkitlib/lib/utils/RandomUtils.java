/*
 * StBukkitLib - RandomUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import java.util.Random;

public final class RandomUtils {

    public final static String getRandomString(int length) {
        return getRandomString(length, false);
    }

    public final static String getRandomString(int length, boolean withNumbers) {
        char[] chars = withNumbers ? "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray() : "abcdefghijklmnopqrstuvwxyz".toCharArray();

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * Generates a random integer within a specified range
     *
     * @param min
     * @param max
     * @return
     */
    public final static int getRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}