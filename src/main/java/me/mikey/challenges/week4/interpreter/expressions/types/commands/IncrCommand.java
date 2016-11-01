package me.mikey.challenges.week4.interpreter.expressions.types.commands;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.types.BBCommand;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 27/10/16.
 */
public class IncrCommand extends BBCommand {
    public IncrCommand(Token command, BBArgList argList) {
        super(command, argList);
    }

    @Override
    public void execute(BBVirtualMachine vm) {
        Token variable = argList.getArgList().get(0);
        vm.setVariable(variable.getData(), vm.getVariableValue(variable.getData(), Integer.class) + 1);
    }
}
