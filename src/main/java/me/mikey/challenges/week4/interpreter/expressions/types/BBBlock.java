package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.InterpreterEventManager;
import me.mikey.challenges.week4.interpreter.exceptions.BBExecutionException;
import me.mikey.challenges.week4.interpreter.exceptions.FunctionAlreadyExistsException;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 27/10/16.
 */
public class BBBlock extends BBExpression {
    protected List<BBExpression> expressions = new ArrayList<>();
    private Map<String, BBFunction> functions = new HashMap<>();
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
                try {
                    expression.execute(vm);

                    if (expression instanceof BBCommand) {
                        InterpreterEventManager.getInstance().instructionExecuted((BBCommand) expression, vm);
                    }
                } catch (BBExecutionException e) {
                    e.printStackTrace();
                    return;
                }
            } else if(control != null && control.shouldExecuteAgain(vm)) {
                execute(vm);
            }
        }
    }

    public void registerFunction(BBFunction function) throws FunctionAlreadyExistsException {
        if(this.functions.containsKey(function.getName())) {
            throw new FunctionAlreadyExistsException("Function " + function.getName() + " has already been defined!");
        }

        System.out.println("Registering function " + function.getName());
        this.functions.put(function.getName(), function);
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
