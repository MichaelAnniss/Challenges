package me.mikey.challenges.week8.commands;

import me.mikey.challenges.week8.exceptions.InvalidCommandException;
import me.mikey.challenges.week8.models.Item;
import me.mikey.challenges.week8.models.Player;
import me.mikey.challenges.week8.models.Room;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Michael on 06/12/2016.
 */
public class PickupCommand extends Command {
    @Override
    public void execute(String command, List<String> args) throws InvalidCommandException {
        Room room = Player.getInstance().getCurrentRoom();

        if(room.getMonsters().size() > 0) {
            throw new InvalidCommandException("You can't pick an item up while there are monsters in the room!");
        }

        if(room.getItemsOnFloor().size() > 0) {
            Player.getInstance().getInventory().addAll(room.getItemsOnFloor());
            System.out.println("Picked up " + room.getItemsOnFloor().stream().map(Item::getName).collect(Collectors.joining(", ")));
            room.getItemsOnFloor().clear();
        } else {
            throw new InvalidCommandException("There are no items to pick up!");
        }
    }
}
