package me.mikey.challenges.week7;

import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 28/11/2016.
 */
public class Benchmarker {
    private String name;
    private long start;

    public Benchmarker(String name) {
        this.name = name;
        this.start = System.nanoTime();
    }

    public void stop() {
        System.out.println("[" + this.name + "] " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - this.start, TimeUnit.NANOSECONDS) + "ms");
    }
}
