package me.mikey.challenges.week4.interpreter.expressions;

import me.mikey.challenges.week4.interpreter.Token;

import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public class BBArgList {
    private List<Token> preArgList;
    private List<Token> argList;

    public BBArgList(List<Token> preArgList, List<Token> argList) {
        this.preArgList = preArgList;
        this.argList = argList;
    }

    public List<Token> getPreArgList() {
        return preArgList;
    }

    public List<Token> getArgList() {
        return argList;
    }

    @Override
    public String toString() {
        String toStr = "BBArgList Pre {";

        for(Token token : preArgList)
            toStr += token + " , ";

        toStr += "} Post {";

        for(Token token : argList)
            toStr += token + " , ";

        toStr = toStr.substring(0, toStr.length() - 3);
        toStr += "}";
        return toStr;
    }
}
