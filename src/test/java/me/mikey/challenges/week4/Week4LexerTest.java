package me.mikey.challenges.week4;

import me.mikey.challenges.week4.interpreter.BBLexer;
import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.UnexpectedTokenException;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Michael on 29/10/16.
 */
public class Week4LexerTest {
    @Test
    public void testLexer() {
        try {
            List<Token> tokens = BBLexer.lex("clear X;");

            //should be 3 + main + endmain
            assertTrue(tokens.size() == 5);
            assertTrue(tokens.get(0).getType() == TokenType.MAIN);
            assertTrue(tokens.get(1).getType() == TokenType.CLEAR);
            assertTrue(tokens.get(2).getType() == TokenType.VARIABLE && tokens.get(2).getData().equals("X"));
            assertTrue(tokens.get(3).getType() == TokenType.SEMICOLON);
            assertTrue(tokens.get(4).getType() == TokenType.ENDMAIN);
        } catch (BBException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test(expected = Test.None.class)
    public void testValidVariables() throws BBException {
        //test some valid examples to not throw exceptions
        BBLexer.lex("clear X_;");
        BBLexer.lex("clear X1");
        BBLexer.lex("clear x1");
    }

    /*@Test(expected = UnexpectedTokenException.class)
    public void testInvalidVariable() throws BBException {
        BBLexer.lex("clear X;");
    }*/
}
