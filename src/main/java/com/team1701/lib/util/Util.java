package com.team1701.lib.util;

/**
 * Contains basic functions that are used often.
 */
@SuppressWarnings("all")
public class Util {
    /**
     * Limits the given input to the given magnitude.
     * @param v Value Input
     * @param min Minimum value of input
     * @param max Maximum value of input
     * @return Value
     */
    private static double limit(double v, double min, double max) {
        return Math.min(max, Math.max(min, v));
    }

    public static boolean epsilonEquals(double a, double b, double epsilon) {
        return (a - epsilon <= b) && (a + epsilon >= b);
    }

    public static boolean epsilonEquals(int a, int b, int epsilon) {
        return (a - epsilon <= b) && (a + epsilon >= b);
    }

}
