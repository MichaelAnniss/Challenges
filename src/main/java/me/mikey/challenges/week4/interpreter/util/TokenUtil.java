package me.mikey.challenges.week4.interpreter.util;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedInput;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Michael on 27/10/16.
 */
public class TokenUtil {
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]?");

    public static boolean matches(List<Token> tokens, List<ExpectedInput> inputTypes) {
        if(tokens.size() != inputTypes.size())
            return false;

        for(int i = 0; i < tokens.size(); i++) {
            if (!inputTypes.get(i).matches(tokens.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static String reportError(List<Token> tokens, List<ExpectedInput> inputTypes) {
        int i = 0;
        String ret = "";

        for(ExpectedInput expectedInput : inputTypes) {

            if(i < tokens.size()) {
                if (!expectedInput.matches(tokens.get(i))) {
                    ret += ("" + expectedInput + " but received " + tokens.get(i)) + "\n";
                }
            } else {
                ret += ("" + expectedInput + " but received nothing!") + "\n";
            }

            i++;
        }

        return ret;
    }

    public static boolean isOperator(TokenType tokenType) {
        return tokenType == TokenType.NOT; //TODO expand
    }

    public static boolean isValidVariable(String variableName) {
        return variableName.length() >= 1 && VARIABLE_PATTERN.matcher(variableName).matches();
    }
}
