package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.InvalidFunctionException;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.HashMap;

/**
 * Created by Michael on 05/11/16.
 */
public class BBFunction extends BBBlock {
    private String name;
    private BBParamList paramList;

    public BBFunction(String name, BBParamList paramList) {
        super(new BBControl());
        this.name = name;
        this.paramList = paramList;
    }

    public void executeFunction(BBParamList list, BBVirtualMachine root) throws BBException {
        //Create a new virtual machine and execute it
        BBVirtualMachine vm = new BBVirtualMachine(this, new HashMap<>());

        //Copy variables from root vm to new function vm
        int i = 0;

        if(list.getArgs().size() != this.paramList.getArgs().size()) {
            throw new InvalidFunctionException("Mismatched number of parameters for function " + name + " call!");
        }

        for(Token token : list.getArgs()) {
            System.out.println("Setting " + paramList.getArgs().get(i).getData() + " to " + root.getVariable(token.getData()).getValue());
            vm.setVariable(paramList.getArgs().get(i).getData(), root.getVariable(token.getData()).getValue());
            i++;
        }

        vm.execute();

        i = 0;
        for(Token token : paramList.getArgs()) {
            System.out.println("Setting back " + list.getArgs().get(i).getData() + " to " + vm.getVariable(token.getData()).getValue());
            root.setVariable(list.getArgs().get(i).getData(), vm.getVariable(token.getData()).getValue());
            i++;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return toString(2);
    }

    public String toString(int indent) {
        String ret = "";
        String spaces = "";

        for(int i = 0; i < indent; i++) {
            spaces += " ";
        }

        ret += "(BBFunction " + name + ")\n";
        for(BBExpression expr : expressions) {
            if(expr instanceof BBBlock) {
                int newIndent = indent + 4;
                ret += (spaces + ((BBBlock) expr).toString(newIndent)) + "\n";
            } else {
                ret += (spaces + expr) + "\n";
            }
        }

        return ret;
    }
}
