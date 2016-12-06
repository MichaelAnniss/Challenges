package me.mikey.challenges.week8.items;

import me.mikey.challenges.week8.models.Item;
import me.mikey.challenges.week8.models.Monster;
import me.mikey.challenges.week8.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 06/12/2016.
 */
public abstract class WeaponItem extends Item {
    public WeaponItem(String name) {
        super(name);
    }

    @Override
    public void use() {
        // Damage all monsters in the room because why not
        List<Monster> monsters = new ArrayList<>(Player.getInstance().getCurrentRoom().getMonsters());
        monsters.forEach(m -> m.damage(getDamage()));
    }

    protected abstract int getDamage();
}
