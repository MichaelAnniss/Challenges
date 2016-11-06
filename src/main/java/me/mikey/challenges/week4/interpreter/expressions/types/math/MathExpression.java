package me.mikey.challenges.week4.interpreter.expressions.types.math;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.InvalidCommandException;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Michael on 04/11/16.
 */
public class MathExpression extends BBExpression {
    private Queue<Token> rpn;

    public MathExpression(Queue<Token> rpn) {
        this.rpn = rpn;
    }

    @Override
    public void execute(BBVirtualMachine vm) {}

    public double executeExpression(BBVirtualMachine vm) throws BBException {
        Queue<Token> tokens = new ArrayDeque<>(this.rpn);
        Iterator<Token> it = tokens.iterator();

        Stack<Double> stack = new Stack<>();

        while(it.hasNext()) {
            Token next = it.next();
            TokenType nextType = next.getType();

            if(nextType == TokenType.NUMBER || nextType == TokenType.VARIABLE) {
                stack.push(Double.parseDouble(toVariable(next, vm).getValue().toString()));
            }
             /*
            else if(nextType == TokenType.FUNCTION_CALL) {
                stack.push()
            }*/

            else if(nextType == TokenType.OP_PLUS) {
                stack.push(stack.pop() + stack.pop());
            }
            else if(nextType == TokenType.OP_MINUS) {
                stack.push(-stack.pop() + stack.pop());
            }
            else if(nextType == TokenType.OP_MULTIPLY) {
                stack.push(stack.pop() * stack.pop());
            }
            else if(nextType == TokenType.OP_DIVIDE) {
                double divisor = stack.pop();
                stack.push(stack.pop() / divisor);
            }
            else {
                throw new InvalidCommandException("Invalid math expression evaluated!");
            }
        }

        if(stack.size() == 1) {
            return stack.pop();
        }
        else {
            throw new InvalidCommandException("Invalid math expression evaluated!");
        }
    }
}
