package me.mikey.challenges.week4.interpreter.exceptions;

/**
 * Created by Michael on 05/11/16.
 */
public class InvalidMathExpressionException extends BBException {
    public InvalidMathExpressionException(String msg) {
        super(msg);
    }
}
