package me.mikey.challenges.week8.commands;

import me.mikey.challenges.week8.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 06/12/2016.
 */
public class CommandManager {
    private static CommandManager instance = new CommandManager();

    private Map<String, Command> commands = new HashMap<>();

    private CommandManager() {
        this.commands.put("move", new MoveCommand());
        this.commands.put("pickup", new PickupCommand());
        this.commands.put("fight", new FightCommand());
        this.commands.put("equip", new EquipCommand());
        this.commands.put("use", new UseCommand());
    }

    public static CommandManager getInstance() {
        return instance;
    }

    public void parseCommand(String command, List<String> args) throws InvalidCommandException {
        if(commands.containsKey(command))
            commands.get(command).execute(command, args);
        else
            throw new InvalidCommandException("Invalid command!");
    }
}
