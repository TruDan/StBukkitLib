/*
 * StBukkitLib - DateUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

    /**
     * Converts a date to string with a specified format
     *
     * @param date
     * @param format
     * @return
     */
    public final static Date stringToDate(String date, SimpleDateFormat format) {
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}