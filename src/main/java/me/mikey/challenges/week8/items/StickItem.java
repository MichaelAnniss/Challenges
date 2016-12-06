package me.mikey.challenges.week8.items;

import me.mikey.challenges.week8.models.Item;

/**
 * Created by Michael on 06/12/2016.
 */
public class StickItem extends Item {
    public StickItem() {
        super("Stick");
    }

    @Override
    public void use() {
        System.out.println("What do you expect to do with a stick?");
    }
}
