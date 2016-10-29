package me.mikey.challenges.week4.interpreter.expressions.types.controls;

import me.mikey.challenges.week4.interpreter.expressions.types.BBBoolean;
import me.mikey.challenges.week4.interpreter.expressions.types.BBControl;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 27/10/16.
 */
public class BBWhile extends BBControl {
    private BBBoolean condition;

    public BBWhile(BBBoolean condition) {
        this.condition = condition;
    }

    @Override
    public boolean shouldExecuteAgain(BBVirtualMachine vm) {
        return condition.isTrue(vm);
    }

    @Override
    public String toString() {
        return "(BBWhile (" + condition.toString() + ") )";
    }
}
