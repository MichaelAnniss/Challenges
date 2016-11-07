package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.exceptions.BBExecutionException;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.List;

/**
 * Created by Michael on 07/11/16.
 */
public class BBParamList extends BBExpression {
    private List<Token> args;

    public BBParamList(List<Token> args) {
        this.args = args;
    }

    @Override
    public void execute(BBVirtualMachine vm) throws BBExecutionException {
    }

    public List<Token> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "(BBParamList {" + args + "} )";
    }
}
