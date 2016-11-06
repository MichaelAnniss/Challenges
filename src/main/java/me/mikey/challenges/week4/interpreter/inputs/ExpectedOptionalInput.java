package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public class ExpectedOptionalInput extends ExpectedInput {
    private TokenType expected;

    public ExpectedOptionalInput(TokenType expected) {
        this.expected = expected;
    }

    @Override
    public ExpectedInputResponse evaluate(List<Token> input) throws BBException {
        if(input.size() < 0)
            return new ExpectedInputResponse(Arrays.asList(), true, null);

        if(input.get(0).getType() == this.expected)
            return new ExpectedInputResponse(Arrays.asList(input.remove(0)), true, null);

        return new ExpectedInputResponse(Arrays.asList(), true, null);
    }
}
