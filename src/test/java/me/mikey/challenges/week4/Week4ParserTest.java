package me.mikey.challenges.week4;

import me.mikey.challenges.week4.interpreter.BBLexer;
import me.mikey.challenges.week4.interpreter.BBParser;
import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.expressions.types.BBBlock;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Michael on 29/10/16.
 */
public class Week4ParserTest {
    @Test
    public void testParserAndVM() {
        try {

            List<Token> tokens = BBLexer.lex("clear X;");
            BBBlock block = BBParser.parse(tokens);

            //BBControl followed by BBCommand
            assertTrue(block.getExpressions().size() == 2);

            tokens = BBLexer.lex("clear X; incr X; incr X;");
            block = BBParser.parse(tokens);

            BBVirtualMachine vm = new BBVirtualMachine(block);

            assertTrue(block.getExpressions().size() == 4);
            vm.execute();
            assertTrue(vm.getVariableValue("X", Integer.class) == 2);
        } catch (BBException e) {
            fail(e.getMessage());
        }
    }
}