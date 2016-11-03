package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.exceptions.*;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.types.BBEndBlock;
import me.mikey.challenges.week4.interpreter.expressions.types.BBBlock;
import me.mikey.challenges.week4.interpreter.expressions.types.BBBoolean;
import me.mikey.challenges.week4.interpreter.expressions.types.BBControl;
import me.mikey.challenges.week4.interpreter.expressions.types.commands.ClearCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.commands.DecrCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.commands.EqualsCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.commands.IncrCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.controls.BBWhile;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedInput;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedOptionalInput;
import me.mikey.challenges.week4.interpreter.util.TokenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by Michael on 27/10/16.
 */
public class BBParser {
    public static BBBlock parse(List<Token> tokens) throws BBException {
        Stack<BBBlock> blockStack = new Stack<>();

        ListIterator<Token> tokenIt = tokens.listIterator();

        while (tokenIt.hasNext()) {
            Token curToken = tokenIt.next();
            TokenType curTokenType = curToken.getType();

            if(curTokenType == TokenType.SEMICOLON)
                continue;

            List<Token> preInputs = new ArrayList<>();
            List<Token> inputs = new ArrayList<>();

            //Scroll back to start of previous tokens as they need to be added in order

            if(curTokenType.preExpectedInputs().size() != 0)
                tokenIt.previous();

            for(ExpectedInput input : curTokenType.preExpectedInputs()) {
                if(!(input instanceof ExpectedOptionalInput)) {
                    tokenIt.previous();
                }
            }

            //needs to ignore optional inputs because they might not be necessary
            for(ExpectedInput input : curTokenType.preExpectedInputs()) {
                if(!(input instanceof ExpectedOptionalInput)) {
                    preInputs.add(tokenIt.next());
                }
            }

            if(curTokenType.preExpectedInputs().size() != 0)
                tokenIt.next();

            for (int i = 0; i < curTokenType.expectedInputs().size(); i++) {
                if (tokenIt.nextIndex() != tokens.size()) {
                    inputs.add(tokenIt.next());
                }
            }

            if(TokenUtil.matches(inputs, curTokenType.expectedInputs()) && TokenUtil.matches(preInputs, curTokenType.preExpectedInputs())) {
                // Now to build the Expression
                if(curTokenType.getExpressionType() == TokenType.ExpressionType.COMMAND) {
                    BBBlock curBlock = blockStack.peek();

                    if(curTokenType == TokenType.INCR) {
                        curBlock.addExpression(new IncrCommand(curToken, new BBArgList(preInputs, inputs)));
                    }

                    else if(curTokenType == TokenType.DECR) {
                        curBlock.addExpression(new DecrCommand(curToken, new BBArgList(preInputs, inputs)));
                    }

                    else if(curTokenType == TokenType.CLEAR) {
                        curBlock.addExpression(new ClearCommand(curToken, new BBArgList(preInputs, inputs)));
                    }

                    else if(curTokenType == TokenType.EQUALS) {
                        curBlock.addExpression(new EqualsCommand(curToken, new BBArgList(preInputs, inputs)));
                    }
                    else {
                        throw new InvalidCommandException("Unknown command type " + curTokenType + " at line " + curToken.getLineNumber());
                    }

                }

                else if(curTokenType.getExpressionType() == TokenType.ExpressionType.BLOCK) {
                    BBControl control = new BBControl();

                    if(curTokenType == TokenType.WHILE) {
                        control = new BBWhile(new BBBoolean(inputs.subList(0, inputs.size() - 2)));
                    }

                    blockStack.add(new BBBlock(control));
                }

                else if(curTokenType.getExpressionType() == TokenType.ExpressionType.BLOCKEND) {
                    if(blockStack.size() > 1) {
                        BBBlock lastStack = blockStack.pop();
                        lastStack.addExpression(new BBEndBlock());
                        blockStack.peek().addExpression(lastStack);
                    }
                }
                /*else {
                    throw new InvalidExpressionTypeException("Unknown expression type " + curTokenType.getExpressionType() + " for token " + curToken + " at line " + curToken.getLineNumber());
                }*/
            } /*else {
                throw new InvalidExpressionException("Invalid expression at line " + curToken.getLineNumber(), inputs, curToken);
            }*/
        }

        //expressions.forEach(System.out::println);
        if(blockStack.size() > 1) {
            throw new UnexpectedEOFException("Unexpected end of file! (Missing end;?)");
        }

        return blockStack.pop();
    }
}
