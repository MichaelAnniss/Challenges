package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;

/**
 * Created by Michael on 27/10/16.
 */
public class ExpectedInput {
    private TokenType expected;

    protected ExpectedInput() {
    }

    public ExpectedInput(TokenType expected) {
        this.expected = expected;
    }

    public InputResponse matches(Token token) {
        return token.getType() == this.expected ? InputResponse.MATCHES : InputResponse.NO_MATCH;
    }

    @Override
    public String toString() {
        return String.format("(Expected %s", this.expected);
    }

    public enum InputResponse {
        MATCHES,
        NO_MATCH,
        IGNORE
    }
}
