package com.apnozhan.villa.views.utils;

/**
 * Created by Y50-70 on 1/22/2018.
 */

public class FormatHelper {
    private static String[] persianNumbers = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};

    public static String toPersianNumber(String text) {
        if (text.isEmpty())
            return "";
        String out = "";
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                out += persianNumbers[number];
            } else if (c == '٫') {
                out += '،';
            } else {
                out += c;
            }

        }
        return out;
    }

    public static String toEnglishNumber(String text) {
        if (text == null || text.isEmpty()) {
            return "0";
        } else {
            String out = "";
            int length = text.length();
            for (int i = 0; i < length; i++) {
                String c = text.substring(i, i + 1);
                boolean isNumber = false;
                for (int j = 0; j < 10; j++) {
                    if (persianNumbers[j].equals(c)) {
                        out += j + "";
                        isNumber = true;
                    }
                }
                if (!isNumber) {
                    out += c;
                }

            }
            return out;
        }
    }


    public static String getPrice(int price) {
        String toReturn = "";
        while (price > 999) {
            int i = price % 1000;
            String toAdd = ",";
            if (i < 100) {
                toAdd += "0";
            }
            if (i < 10) {
                toAdd += "0";
            }
            toAdd += i;
            toReturn = toAdd + toReturn;
            price -= i;
            price = price / 1000;
        }
        toReturn = price + toReturn;
        return toReturn;
    }

    public static boolean hasPersian(String str) {
        for (int i = 0; i < str.length(); i++) {
            int c = (int) str.charAt(i);
            if (c >= 0x0600 && c <= 0x06FF) {
                return true;
            } else if (c >= 0xFB50 && c <= 0xFDFF) {
                return true;
            } else if (c > 0xFE70 && c <= 0xFEFF) {
                return true;
            }
        }
        return false;
    }
}