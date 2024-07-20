package com.gl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToNumber {
    public static int stringToNumber(String str) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(str);
        str = matcher.replaceAll("").trim();
        if (str.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(str);
    }

}
