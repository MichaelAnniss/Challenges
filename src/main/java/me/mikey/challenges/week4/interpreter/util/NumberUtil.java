package me.mikey.challenges.week4.interpreter.util;

/**
 * Created by Michael on 27/10/16.
 */
public class NumberUtil {
    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
