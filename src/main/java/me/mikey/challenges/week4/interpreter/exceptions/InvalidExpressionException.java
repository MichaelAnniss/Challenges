package me.mikey.challenges.week4.interpreter.exceptions;

import me.mikey.challenges.week4.interpreter.Token;

import java.util.List;

/**
 * Created by Michael on 28/10/16.
 */
public class InvalidExpressionException extends BBException {
    public InvalidExpressionException(String message, List<Token> inputs, Token curToken) {
        super(message + "\n");// + TokenUtil.reportError(inputs, curToken.getType().expectedInputs()));
    }//TODO
}
