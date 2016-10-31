package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.InterpreterEventManager;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public class BBBlock extends BBExpression {
    private List<BBExpression> expressions = new ArrayList<>();
    private BBControl control;

    public BBBlock(BBControl control) {
        this.control = control;
        this.expressions.add(control);
    }

    public void addExpression(BBExpression expr) {
        this.expressions.add(expr);
    }

    @Override
    public void execute(BBVirtualMachine vm) {
        BBControl control = null;

        for(BBExpression expression : expressions) {
            if(expression instanceof BBControl) {
                control = (BBControl) expression;
            } else if( !(expression instanceof BBEndBlock)) {
                expression.execute(vm);

                if(expression instanceof BBCommand) {
                    InterpreterEventManager.getInstance().instructionExecuted((BBCommand) expression, vm);
                }
            } else if(control != null && control.shouldExecuteAgain(vm)) {
                execute(vm);
            }
        }
    }

    public List<BBExpression> getExpressions() {
        return expressions;
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

        ret += "(BBBlock " + control + ")\n";
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
