package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.HashMap;

/**
 * Created by Michael on 05/11/16.
 */
public class BBFunction extends BBBlock {
    private String name;
    private BBArgList list;

    public BBFunction(String name, BBArgList list) {
        super(new BBControl());
        this.name = name;
        this.list = list;
    }

    public void executeFunction(BBArgList list, BBVirtualMachine root) {
        //Create a new virtual machine and execute it
        BBVirtualMachine vm = new BBVirtualMachine(this, new HashMap<>());

        //Initialise variables...
        int i = 0;
        for(Token token : list.getArgList()) {
            if(token.getType() == TokenType.VARIABLE) {
                vm.setVariable(this.list.getArgList().get(i).getData(),
                        root.getVariable(token.getData()).getValue());
            }

            i++;
        }

        vm.execute();

        //Set variables back
        i = 0;
        for(Token token : this.list.getArgList()) {
            if(token.getType() == TokenType.VARIABLE) {
                root.setVariable(list.getArgList().get(i).getData(),
                        vm.getVariable(token.getData()).getValue());
            }

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
