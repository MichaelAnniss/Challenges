package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 27/10/16.
 */
public class BBCommand extends BBExpression {
    protected Token command;
    protected BBArgList argList;

    public BBCommand(Token command, BBArgList argList) {
        this.command = command;
        this.argList = argList;
    }

    public Token getCommand() {
        return command;
    }

    public BBArgList getArgList() {
        return argList;
    }

    @Override
    public String toString() {
        return String.format("(BBCommand %s %s)", command, argList);
    }

    @Override
    public void execute(BBVirtualMachine vm) {
        throw new UnsupportedOperationException("BBCommand cannot be executed directly!");
    }
}
