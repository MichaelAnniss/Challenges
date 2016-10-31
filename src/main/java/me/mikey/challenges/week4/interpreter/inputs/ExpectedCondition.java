package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;

/**
 * Created by Michael on 27/10/16.
 */
public class ExpectedCondition extends ExpectedInput {
    @Override
    public InputResponse matches(Token token) {
        return token.getType() == TokenType.NOT ? InputResponse.MATCHES : InputResponse.NO_MATCH;
    }
}
