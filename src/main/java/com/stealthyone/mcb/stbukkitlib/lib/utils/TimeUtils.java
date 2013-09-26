package com.stealthyone.mcb.stbukkitlib.lib.utils;

public final class TimeUtils {

    /**
     * Translates seconds into X days, X hours, X minutes, X seconds
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
			returnString.append(days + (days > 1 || days == 0 ? " days" : " day"));
		}
		if (hours > 0) {
            if (days > 0) {
                returnString.append(" ");
            }
			returnString.append(hours + (hours > 1 || hours == 0 ? " hours" : " hour"));
		}
		if (minutes > 0) {
            if (hours > 0) {
                returnString.append(" ");
            }
			returnString.append(minutes + (minutes > 1 || minutes == 0 ? " minutes" : " minute"));
		}
        if (seconds >= 0 && seconds == 0 && days == 0 && hours == 0 && minutes == 0) {
            if (minutes > 0) {
                returnString.append(" ");
            }
            returnString.append(seconds + (seconds > 1 || seconds == 0 ? " seconds" : " second"));
        }

		return returnString.toString();
	}
	
}