package me.mikey.challenges.week7;

/**
 * Created by Michael on 28/11/2016.
 */
public class TrigCache {
    private static final double[] cos;
    private static final double[] sin;

    static double cos(int t) {
        t = t % 360;
        return cos[t];
    }

    static double sin(int t) {
        t = t % 360;
        return sin[t];
    }

    static {
        cos = new double[361];
        sin = new double[361];

        for(int t = 0; t <= 360; t++) {
            cos[t] = Math.cos(Math.toRadians(t));
            sin[t] = Math.sin(Math.toRadians(t));
        }
    }
}
