package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.UnexpectedTokenException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michael on 27/10/16.
 */
public class BBLexer {
    public static List<Token> lex(String input) throws BBException {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.MAIN, "main", 0));
        String[] lines = input.split("\n");

        int lineNumber = 1;

        for(String line : lines) {
            while (!line.equals("")) {
                line = line.trim();

                boolean matched = false;

                for (TokenType type : TokenType.values()) {
                    if (type.getPattern() == null)
                        continue;

                    Matcher matcher = type.getPattern().matcher(line);

                    //Special cases (can't really avoid these...)
                    if(type == TokenType.FUNCTION) {
                        if(matcher.find()) {
                            String functionName = matcher.group().trim().split(" ")[1];
                            tokens.add(new Token(TokenType.FUNCTION, functionName, lineNumber));
                            line = matcher.replaceFirst("").trim();
                            continue;
                        }
                    }

                    if(type == TokenType.FUNCTION_CALL) {
                        if(matcher.find()) {
                            tokens.add(new Token(TokenType.FUNCTION_CALL, matcher.group().trim().substring(0, matcher.group().length() - 1), lineNumber));
                            line = "(" + matcher.replaceFirst("").trim();
                            continue;
                        }
                    }

                    if (matcher.find()) {
                        String token = matcher.group().trim();

                        if(type != TokenType.COMMENT)
                            tokens.add(new Token(type, token, lineNumber));

                        line = matcher.replaceFirst("").trim();
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    throw new UnexpectedTokenException("Unexpected token " + line + " at line " + lineNumber + " - invalid variable name?");
                }
            }

            lineNumber++;
        }

        tokens.add(new Token(TokenType.ENDMAIN, "main", lineNumber));
        return tokens;
    }
}
