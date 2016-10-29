package me.mikey.challenges.week4.interpreter;

/**
 * Created by Michael on 27/10/16.
 */
public class Token {
    private TokenType type;
    private String data;
    private int lineNumber;

    public Token(TokenType type, String data, int lineNumber) {
        this.type = type;
        this.data = data;
        this.lineNumber = lineNumber;
    }

    public TokenType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return String.format("(%s %s):%d", type.name(), data, lineNumber);
    }
}
