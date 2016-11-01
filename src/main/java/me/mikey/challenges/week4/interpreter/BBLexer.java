package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.UnexpectedTokenException;
import me.mikey.challenges.week4.interpreter.util.TokenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Michael on 27/10/16.
 */
public class BBLexer {
    public static List<Token> lex(String input) throws BBException {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.MAIN, "main", 0));
        String[] lines = input.split("\n");

        int lineNumber = 1;

        lineLoop: for(String line : lines) {
            StringTokenizer lineTokenizer = new StringTokenizer(line, "( |;)", true);

            while(lineTokenizer.hasMoreTokens()) {
                String token = lineTokenizer.nextToken().trim();
                boolean matched = false;

                //Ignore whitespace
                if(token.isEmpty()) continue;

                //Handle comments first, just skip the rest of the line
                if(TokenType.COMMENT.matches(token)) {
                    continue lineLoop;
                }

                for(TokenType type : TokenType.values()) {
                    if(type.matches(token)) {
                        tokens.add(new Token(type, token, lineNumber));
                        matched = true;
                        break;
                    }
                }

                if(!matched && TokenUtil.isValidVariable(token)) {
                    tokens.add(new Token(TokenType.VARIABLE, token, lineNumber));
                } else if(!matched) {
                    throw new UnexpectedTokenException("Unexpected token " + token + " at line " + lineNumber + " - invalid variable name?");
                }
            }

            lineNumber++;
        }

        tokens.add(new Token(TokenType.ENDMAIN, "main", lineNumber));
        tokens.add(new Token(TokenType.SEMICOLON, ";", lineNumber));
        return tokens;
    }
}
