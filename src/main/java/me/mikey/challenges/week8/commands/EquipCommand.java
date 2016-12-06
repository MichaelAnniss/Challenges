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
public class EquipCommand extends Command {
    @Override
    public void execute(String command, List<String> args) throws InvalidCommandException {
        if(args.size() < 1) {
            throw new InvalidCommandException("What do you want to equip?");
        }

        String itemName = args.get(0);

        Optional<Item> o = Player.getInstance().getInventory().stream().filter(i -> i.getName().equalsIgnoreCase(itemName)).findFirst();

        if(!o.isPresent()) {
            throw new InvalidCommandException("Unknown item " + itemName);
        }

        Item item = o.get();

        if(item instanceof WeaponItem) {
            Player.getInstance().setCurrentWeapon((WeaponItem) item);
            Player.getInstance().getInventory().remove(item);
            System.out.println("Equipped " + itemName + "!");
        } else {
            throw new InvalidCommandException("That is not a weapon!");
        }
    }
}
