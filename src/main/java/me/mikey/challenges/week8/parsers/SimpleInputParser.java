package me.mikey.challenges.week8.parsers;

import me.mikey.challenges.week8.InputParser;
import me.mikey.challenges.week8.commands.CommandManager;
import me.mikey.challenges.week8.exceptions.InvalidCommandException;
import me.mikey.challenges.week8.models.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Michael on 06/12/2016.
 */
public class SimpleInputParser implements InputParser {

    private Scanner scanner;

    public SimpleInputParser() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void readLine() {
        String input = this.scanner.nextLine().toLowerCase().trim();

        if(input.equals("done")) {
            World.getInstance().setFinishing(true);
        } else {
            String command = input.split(" ")[0];
            List<String> args = new ArrayList<>();

            for(int i = 1; i < input.split(" ").length; i++) {
                args.add(input.split(" ")[i]);
            }

            try {
                CommandManager.getInstance().parseCommand(command, args);
            } catch (InvalidCommandException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
