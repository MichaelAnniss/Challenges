package me.mikey.challenges.week8.items.weapons;

import me.mikey.challenges.week8.items.WeaponItem;

/**
 * Created by Michael on 06/12/2016.
 */
public class SwordItem extends WeaponItem {
    public SwordItem() {
        super("Sword");
    }

    @Override
    protected int getDamage() {
        return 10;
    }
}
