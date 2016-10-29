package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;

/**
 * Created by Michael on 27/10/16.
 */
public class ExpectedInputOr extends ExpectedInput {
    private TokenType expected;
    private TokenType expected2;

    public ExpectedInputOr(TokenType expected, TokenType expected2) {
        this.expected = expected;
        this.expected2 = expected2;
    }

    @Override
    public boolean matches(Token token) {
        return token.getType() == this.expected || token.getType() == this.expected2;
    }

    @Override
    public String toString() {
        return String.format("(ExpectedInputOr %s or %s", expected, expected2);
    }
}
