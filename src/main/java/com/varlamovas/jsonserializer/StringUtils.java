package com.varlamovas.jsonserializer;

public class StringUtils {

    public static boolean isWhitespace(String string) {
        if (string == null) return false;
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isNumber(String string) {
        if (string == null) return false;
        for  (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        return true;
    }

    public static String wrapByQuotes(String string) {
        return "\"" + string + "\"";
    }
}
