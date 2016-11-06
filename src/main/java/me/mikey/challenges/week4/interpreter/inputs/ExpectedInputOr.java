package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;

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
    public ExpectedInputResponse evaluate(List<Token> input) throws BBException {
        if(input.size() < 1)
            return new ExpectedInputResponse(Arrays.asList(), false, null);

        if(this.expected.contains(input.get(0).getType())) {
            return new ExpectedInputResponse(Arrays.asList(input.get(0)), true, null);
        }

        return new ExpectedInputResponse(Arrays.asList(), false, null);
    }

    @Override
    public String toString() {
        return String.format("(ExpectedInputOr %s", expected);
    }
}
