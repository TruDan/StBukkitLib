package com.stealthyone.mcb.stbukkitlib.lib.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayUtils {

    public final static String stringArrayToString(String[] input, int fromIndex) {
        return stringArrayToString(input, fromIndex, input.length);
    }

    public final static String stringArrayToString(String[] input, int fromIndex, int toIndex) {
        StringBuilder sb = new StringBuilder();
        List<String> list = Arrays.asList(input).subList(fromIndex, toIndex);
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

}