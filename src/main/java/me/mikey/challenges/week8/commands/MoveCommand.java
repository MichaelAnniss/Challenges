package me.mikey.challenges.week8.commands;

import me.mikey.challenges.week8.RoomDirection;
import me.mikey.challenges.week8.exceptions.InvalidCommandException;
import me.mikey.challenges.week8.exceptions.InvalidMoveException;
import me.mikey.challenges.week8.models.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Michael on 06/12/2016.
 */
public class MoveCommand extends Command {
    @Override
    public void execute(String command, List<String> args) throws InvalidCommandException {
        if(args.size() < 1) {
            throw new InvalidCommandException("Invalid arguments! Direction expected");
        }

        String direction = args.stream().collect(Collectors.joining(" ")).toUpperCase().replace(" ", "_");
        RoomDirection dir = RoomDirection.valueOf(direction);

        try {
            Player.getInstance().moveInDirection(dir);
        } catch (InvalidMoveException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
