package me.mikey.challenges.week4.interpreter.exceptions;

/**
 * Created by Michael on 05/11/16.
 */
public class FunctionAlreadyExistsException extends BBException {
    public FunctionAlreadyExistsException(String message) {
        super(message);
    }
}
