package com.example.administrator.mytravels.utils;

public class MyString {
    public static boolean isEmpty(String s) {
        if (s == null || s.trim().length() == 0)
            return true;
        return false;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
    public static String getMoneyText(double amount) {
        return String.format("%,.2f", amount).replace(".00", "");
    }
}
