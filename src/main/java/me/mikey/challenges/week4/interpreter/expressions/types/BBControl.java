package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 27/10/16.
 */
public class BBControl extends BBExpression {
    public boolean shouldExecuteAgain(BBVirtualMachine vm) {
        return false;
    }

    @Override
    public String toString() {
        return "(BBControl )";
    }

    @Override
    public void execute(BBVirtualMachine vm) {
    }
}
