package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.InvalidArgListException;
import me.mikey.challenges.week4.interpreter.expressions.types.BBParamList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michael on 07/11/16.
 */
public class ExpectedArgListInput extends ExpectedInput {
    @Override
    public ExpectedInputResponse evaluate(List<Token> input) throws BBException {
        boolean expectedComma = false;
        Iterator<Token> tokenIt = input.iterator();
        List<Token> args = new ArrayList<>();
        List<Token> usedTokens = new ArrayList<>();

        while(tokenIt.hasNext()) {
            Token token = tokenIt.next();

            if((token.getType() == TokenType.VARIABLE || token.getType() == TokenType.NUMBER)) {
                if(expectedComma) {
                    throw new InvalidArgListException("Invalid arg list - expected comma but received " + token);
                }

                expectedComma = true;
                args.add(token);
                usedTokens.add(token);
            }

            else if(token.getType() == TokenType.COMMA) {
                if(!expectedComma) {
                    throw new InvalidArgListException("Invalid arg list - unexpected comma " + token + "?");
                }
                expectedComma = false;
                usedTokens.add(token);
            }

            else {
                break;
            }
        }

        return new ExpectedInputResponse(usedTokens, args.size() > 0, new BBParamList(args));
    }
}
