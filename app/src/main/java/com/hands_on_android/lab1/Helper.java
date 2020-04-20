package com.hands_on_android.lab1;

import java.util.Locale;

public class Helper {

    /*
    This is a helper function for this lab. You can input the number of seconds in integer
    The function will return properly formatted String for given integer value, in MM:SS format
    Ex) input -> output
        60 -> 01 : 00
        5 -> 00 : 05
        148 -> 02 : 28
     */
    public static String formatTime(int timeInSeconds) {
        int min = timeInSeconds / 60;
        int sec = timeInSeconds % 60;
        return String.format(Locale.CANADA, "%02d : %02d", min, sec);
    }
}
