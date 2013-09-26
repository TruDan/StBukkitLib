/*
 * StBukkitLib - StringUtils
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.utils;

public final class StringUtils {

	public final static boolean containsMultiple(String main, String... comparisons) {
		for (String cur : comparisons) {
			if (main.contains(cur)) {
				return true;
			}
		}
		return false;
	}
	
	public final static boolean equalsIgnoreCaseMultiple(String main, String... comparisons) {
		for (String cur : comparisons) {
			if (cur.equalsIgnoreCase(main)) {
				return true;
			}
		}
		return false;
	}
	
	public final static boolean startsWithVowel(String input) {
		char c = Character.toUpperCase(input.charAt(0));
		switch (c) {
			case 'A':
			case 'E':
			case 'I':
			case 'O':
			case 'U':
				return true;
			default:
				return false;
		}
	}
}