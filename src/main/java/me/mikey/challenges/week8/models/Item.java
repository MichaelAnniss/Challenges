package me.mikey.challenges.week8.models;

/**
 * Created by Michael on 06/12/2016.
 */
public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void use();

    public String getName() {
        return name;
    }
}
