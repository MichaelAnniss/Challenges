package me.mikey.challenges.week4.interpreter.exceptions;

/**
 * Created by Michael on 28/10/16.
 */
public class UnexpectedEOFException extends BBException {
    public UnexpectedEOFException(String message) {
        super(message);
    }
}
