package me.mikey.challenges.week4.interpreter.inputs;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.InvalidMathExpressionException;
import me.mikey.challenges.week4.interpreter.expressions.types.commands.EqualsCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.math.MathExpression;

import java.util.*;

/**
 * Created by Michael on 02/11/16.
 */
public class ExpectedEvaluableInput extends ExpectedInput {
    @Override
    public ExpectedInputResponse evaluate(List<Token> input) throws BBException {
        boolean done = false;
        ListIterator<Token> it = input.listIterator();

        // Implementation of Shunting-yard algorithm
        // https://en.wikipedia.org/wiki/Shunting-yard_algorithm
        Stack<Token> opStack = new Stack<>();
        Queue<Token> outputQueue = new ArrayDeque<>();

        int i = 0;

        while(!done && it.hasNext()) {
            Token curToken = it.next();

            if(curToken.getType() == TokenType.NUMBER || curToken.getType() == TokenType.VARIABLE) {
                outputQueue.add(curToken);
            }

            else if(curToken.getType() == TokenType.FUNCTION_CALL) {
                opStack.add(curToken);
            }

            else if(curToken.getType() == TokenType.COMMA) {
                boolean added = false;

                while(opStack.size() > 0) {
                    Token token = opStack.pop();

                    if(token.getType() == TokenType.LPAREN) {
                        added = true;
                        break;
                    } else {
                        outputQueue.add(token);
                    }
                }

                if(!added) {
                    throw new InvalidMathExpressionException("No left parenthesis found for " + curToken + " function");
                }
            }

            else if(isOperator(curToken.getType())) {
                TokenType op1 = curToken.getType();

                while(opStack.size() > 0) {
                    TokenType op2 = opStack.peek().getType();

                    if(isOperator(op2) &&
                            (isLeftAssociative(curToken.getType()) && getPrecedence(op1) <= getPrecedence(op2)) ||
                            (isRightAssociative(op1) && getPrecedence(op1) < getPrecedence(op2))) {
                        outputQueue.add(opStack.pop());
                    } else {
                        break;
                    }
                }

                opStack.add(curToken);
            }

            else if(curToken.getType() == TokenType.LPAREN) {
                opStack.add(curToken);
            }

            else if(curToken.getType() == TokenType.RPAREN) {
                boolean added = false;

                while (opStack.size() > 0) {
                    added = true;
                    Token op = opStack.pop();

                    if (op.getType() == TokenType.LPAREN) {
                        break;
                    }

                    outputQueue.add(op);
                }

                if(!added) {
                    throw new InvalidMathExpressionException("Mismatched parentheses " + curToken);
                }
            }

            else {
                done = true;
            }

            i++;
        }

        while(opStack.size() > 0) {
            Token op = opStack.pop();

            if(op.getType() == TokenType.LPAREN || op.getType() == TokenType.RPAREN) {
                throw new InvalidMathExpressionException("Mismatched parentheses " + op);
            }

            outputQueue.add(op);
        }

        //Consume all tokens used by the parser
        List<Token> usedTokens = new ArrayList<>();
        for(int j = 0; j < i; j++) {
            usedTokens.add(input.remove(0));
        }

        return new ExpectedInputResponse(usedTokens, true, new EqualsCommand(null, new MathExpression(outputQueue)));
    }

    public boolean isOperator(TokenType type) {
        return type == TokenType.OP_PLUS ||
                type == TokenType.OP_MINUS ||
                type == TokenType.OP_MULTIPLY ||
                type == TokenType.OP_DIVIDE;
    }

    public boolean isLeftAssociative(TokenType operator) {
        return isOperator(operator);
    }

    public boolean isRightAssociative(TokenType operator) {
        return false;
    }

    public int getPrecedence(TokenType op1) {
        int precedence = 2;

        if(op1 == TokenType.OP_MULTIPLY) precedence = 3;
        if(op1 == TokenType.OP_DIVIDE) precedence = 3;

        return precedence;
    }


}
