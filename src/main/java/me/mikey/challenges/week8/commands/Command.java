package me.mikey.challenges.week8.commands;

import me.mikey.challenges.week8.exceptions.InvalidCommandException;

import java.util.List;

/**
 * Created by Michael on 06/12/2016.
 */
public abstract class Command {
    public abstract void execute(String command, List<String> args) throws InvalidCommandException;
}
