/*
 * StBukkitLib - InputUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InputUtils {

    /**
     * Gets a string from input arguments from a command
     * @param args
     * @param fromIndex
     * @return
     */
    public final static String getInputString(String[] args, int fromIndex) {
        StringBuilder sb = new StringBuilder();
        List<String> list = Arrays.asList(args).subList(fromIndex, args.length);//.toString().replace("[", "").replace("]", "").replace(",", "");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            sb.append(next);
            if (it.hasNext()) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public final static boolean getInputBoolean(String input) {
        input = input.toLowerCase();
        switch (input) {
            case "true":case "yes":
                return true;
            case "false":case "no":
                return false;
        }
        throw new IllegalArgumentException("Unable to parse input to boolean '" + input + "' (accepted values: true/yes/false/no");
    }

}