package me.mikey.challenges.week4.interpreter.exceptions;

/**
 * Created by Michael on 30/10/16.
 */
public class InvalidExpressionTypeException extends BBException {
    public InvalidExpressionTypeException(String message) {
        super(message);
    }
}
