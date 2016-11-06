package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 03/11/16.
 */
public class ExpectedInput {
    private TokenType expected;

    protected ExpectedInput() {
    }

    public ExpectedInput(TokenType expected) {
        this.expected = expected;
    }

    /**
     * Takes a list of all input tokens and returns all of the ones it does not use
     *
     * @param input - list of all tokens
     * @return - response from evaluation
     */
    public ExpectedInputResponse evaluate(List<Token> input) throws BBException {
        if(input.get(0).getType() == expected) {
            return new ExpectedInputResponse(Arrays.asList(input.remove(0)), true, null);
        }

        return new ExpectedInputResponse(Arrays.asList(), false, null);
    }

    @Override
    public String toString() {
        return "ExpectedInput { " + this.expected + " }";
    }

    public class ExpectedInputResponse {
        private List<Token> usedTokens;
        private boolean matches;
        private BBExpression expression;

        public ExpectedInputResponse(List<Token> usedTokens, boolean matches, BBExpression expression) {
            this.usedTokens = usedTokens;
            this.matches = matches;
            this.expression = expression;
        }

        public boolean isMatched() {
            return matches;
        }

        public BBExpression getExpression() {
            return expression;
        }

        public List<Token> getUsedTokens() {
            return usedTokens;
        }
    }

}
