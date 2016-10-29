package me.mikey.challenges.week4.interpreter.exceptions;

/**
 * Created by Michael on 28/10/16.
 */
public class UnexpectedTokenException extends BBException {
    public UnexpectedTokenException(String msg) {
        super(msg);
    }
}
