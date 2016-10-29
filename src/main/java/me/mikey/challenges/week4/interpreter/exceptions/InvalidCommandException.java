package me.mikey.challenges.week4.interpreter.exceptions;

/**
 * Created by Michael on 28/10/16.
 */
public class InvalidCommandException extends BBException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
