package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;

/**
 * Created by Michael on 27/10/16.
 */
public class ExpectedOptionalInput extends ExpectedInput {
    private TokenType expected;

    public ExpectedOptionalInput(TokenType expected) {
        this.expected = expected;
    }

    @Override
    public InputResponse matches(Token token) {
        return token.getType() == expected ? InputResponse.MATCHES : InputResponse.IGNORE;
    }
}
