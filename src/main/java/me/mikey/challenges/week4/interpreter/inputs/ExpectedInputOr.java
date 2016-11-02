package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public class ExpectedInputOr extends ExpectedInput {
    private List<TokenType> expected;

    public ExpectedInputOr(TokenType ... expected) {
        this.expected = Arrays.asList(expected);
    }

    @Override
    public InputResponse matches(Token token) {
        return (this.expected.contains(token.getType())) ? InputResponse.MATCHES : InputResponse.NO_MATCH;
    }

    @Override
    public String toString() {
        return String.format("(ExpectedInputOr %s", expected);
    }
}
