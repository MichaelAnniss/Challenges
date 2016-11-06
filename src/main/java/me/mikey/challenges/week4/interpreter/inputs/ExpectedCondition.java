package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public class ExpectedCondition extends ExpectedInput {
    @Override
    public ExpectedInputResponse evaluate(List<Token> input) throws BBException {
        if(input.size() < 1)
            return new ExpectedInputResponse(Arrays.asList(), false, null);

        Token token = input.get(0);

        if(token.getType() == TokenType.OP_NOT_EQUAL || token.getType() == TokenType.OP_EQUALS) {
            return new ExpectedInputResponse(Arrays.asList(input.remove(0)), true, null);
        }

        return new ExpectedInputResponse(Arrays.asList(), false, null);
    }
}
