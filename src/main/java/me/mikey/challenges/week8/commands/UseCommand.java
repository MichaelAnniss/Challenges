package me.mikey.challenges.week8.commands;

import me.mikey.challenges.week8.exceptions.InvalidCommandException;
import me.mikey.challenges.week8.items.WeaponItem;
import me.mikey.challenges.week8.models.Item;
import me.mikey.challenges.week8.models.Player;

import java.util.List;
import java.util.Optional;

/**
 * Created by Michael on 06/12/2016.
 */
public class UseCommand extends Command {
    @Override
    public void execute(String command, List<String> args) throws InvalidCommandException {
        if(args.size() < 1) {
            throw new InvalidCommandException("What would you like to use?");
        }

        Optional<Item> o = Player.getInstance().getInventory().stream()
                .filter(i -> i.getName().equalsIgnoreCase(args.get(0)))
                .findFirst();

        if(!o.isPresent())
            throw new InvalidCommandException("Unknown item " + args.get(0) + "!");

        Item item = o.get();

        if(item instanceof WeaponItem) {
            throw new InvalidCommandException("You can't use weapons! You must equip them and then fight");
        }

        item.use();
    }
}
