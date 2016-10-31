package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 27/10/16.
 */
public class BBEndBlock extends BBExpression {
    @Override
    public void execute(BBVirtualMachine vm) {
    }

    @Override
    public String toString() {
        return "(BBEndBlock )";
    }
}
