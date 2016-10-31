package me.mikey.challenges.week4.interpreter.events;

import me.mikey.challenges.week4.interpreter.expressions.types.BBCommand;
import me.mikey.challenges.week4.interpreter.vm.BBVariable;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 31/10/16.
 */
public abstract class InterpreterListener {
    public void instructionExecuted(BBCommand command, BBVirtualMachine context) {
    }

    public void variableUpdate(BBVariable variable, Object oldValue, Object newValue, BBVirtualMachine context) {
    }
}
