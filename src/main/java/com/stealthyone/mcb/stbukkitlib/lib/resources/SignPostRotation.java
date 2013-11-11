package com.stealthyone.mcb.stbukkitlib.lib.resources;

public enum SignPostRotation {

    SOUTH((char) 0x0),
    SOUTH_SOUTHWEST((char) 0x1),
    SOUTHWEST((char) 0x2),
    WEST_SOUTHWEST((char) 0x3),
    WEST((char) 0x4),
    WEST_NORTHWEST((char) 0x5),
    NORTH((char) 0x8),
    NORTH_NORTHEAST((char) 0x9),
    NORTHEAST((char) 0xA),
    EAST_NORTHEAST((char) 0xB),
    EAST((char) 0xC),
    EAST_SOUTHEAST((char) 0xD),
    SOUTHEAST((char) 0xE),
    SOUTH_SOUTHEAST((char) 0xF);

    private char data;

    private SignPostRotation(char data) {
        this.data = data;
    }

    public char getChar() {
        return data;
    }

}