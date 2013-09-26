/*
 * StBukkitLib - Verifiable
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://stealthyone.com/>
 *
 * Licensed under the GNU General Public License v2.0
 * View StBukkitLib.java for a detailed notice message.
 */
package com.stealthyone.mcb.stbukkitlib.lib.verification;

public interface Verifiable {

    String getId();

    String getDesc();

    String getQuestion();

    void yes();

    void no();

}