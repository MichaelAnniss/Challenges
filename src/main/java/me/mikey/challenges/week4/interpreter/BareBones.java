package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.expressions.types.BBBlock;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 27/10/16.
 */
public class BareBones {
    public static void evaluate(String code, boolean silent) {
        long nanoTime = System.nanoTime();
        long nanoTimeEnd;

        List<Token> tokens;

        try {
            tokens = BBLexer.lex(code);
        } catch (BBException e) {
            e.printStackTrace();
            return;
        }

        nanoTimeEnd = System.nanoTime();

        if(!silent) {
            tokens.forEach(System.out::println);
            System.out.println("Lexing took " + TimeUnit.MILLISECONDS.convert(nanoTimeEnd - nanoTime, TimeUnit.NANOSECONDS) + "ms");
            System.out.println(" =================== \n\n");
        }

        nanoTime = System.nanoTime();

        BBBlock block;

        try {
            block = BBParser.parse(tokens);
        } catch (BBException e) {
            e.printStackTrace();
            return;
        }

        nanoTimeEnd = System.nanoTime();

        if(!silent) {
            System.out.println(block);
            System.out.println("Parsing took " + TimeUnit.MILLISECONDS.convert(nanoTimeEnd - nanoTime, TimeUnit.NANOSECONDS) + "ms");
            System.out.println(" =================== \n\n");
        }

        nanoTime = System.nanoTime();

        BBVirtualMachine vm = new BBVirtualMachine(block);
        vm.execute();

        if(!silent) {
            System.out.println("Variables");
            vm.shutdown();
        }

        nanoTimeEnd = System.nanoTime();

        if(!silent) {
            System.out.println("VM took " + TimeUnit.MILLISECONDS.convert(nanoTimeEnd - nanoTime, TimeUnit.NANOSECONDS) + "ms");
        }
    }
}
