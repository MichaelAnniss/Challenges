package me.mikey.challenges.week4.interpreter.expressions;

import me.mikey.challenges.week4.interpreter.Token;

import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public class BBArgList {
    private List<Token> argList;

    public BBArgList(List<Token> argList) {
        this.argList = argList;
    }

    public List<Token> getArgList() {
        return argList;
    }

    @Override
    public String toString() {
        String toStr = "BBArgList {";

        for(Token token : argList)
            toStr += token + " , ";

        toStr = toStr.substring(0, toStr.length() - 3);
        toStr += "}";
        return toStr;
    }
}
