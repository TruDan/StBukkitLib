package com.stealthyone.mcb.stbukkitlib.lib.utils;

public final class TimeUtils {

    /**
     * Translates seconds into X days, X hours, X minutes, X seconds
     *
     * @param seconds
     * @return
     */
    public final static String translateSeconds(int seconds) {
        int minutes = seconds / 60;
        seconds = seconds - minutes * 60;
        int hours = minutes / 60;
        minutes = minutes - hours * 60;
        int days = hours / 24;
        hours = hours - days * 24;
        StringBuilder returnString = new StringBuilder();
        if (days > 0) {
            returnString.append(days).append(days == 1 ? " day" : " days");
        }
        if (hours > 0) {
            if (days > 0) {
                returnString.append(" ");
            }
            returnString.append(hours).append(hours == 1 ? " hour" : " hours");
        }
        if (minutes > 0) {
            if (hours > 0) {
                returnString.append(" ");
            }
            returnString.append(minutes).append(minutes == 1 ? " minute" : " minutes");
        }
        if (seconds > 0 || seconds == 0 && minutes == 0 && hours == 0 && days == 0) {
            if (minutes > 0) {
                returnString.append(" ");
            }
            returnString.append(seconds).append(seconds == 1 ? " second" : " seconds");
        }

        return returnString.toString();
    }

}