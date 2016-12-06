package me.mikey.challenges.week8.items.weapons;

import me.mikey.challenges.week8.items.WeaponItem;

/**
 * Created by Michael on 06/12/2016.
 */
public class HandWeaponItem extends WeaponItem {
    public HandWeaponItem() {
        super("Fists!");
    }

    @Override
    protected int getDamage() {
        return 5;
    }
}
