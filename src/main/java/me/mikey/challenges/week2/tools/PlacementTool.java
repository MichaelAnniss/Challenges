package me.mikey.challenges.week2.tools;

import me.mikey.challenges.week2.util.Position;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Michael on 17/10/16.
 */
public abstract class PlacementTool {
    protected static Random RANDOM = new Random();

    public abstract void activate(Position pos);
    public abstract String getName();

    public String getRandomString() {
        return new BigInteger(130, RANDOM).toString(32).substring(0, 5);
    }
}
