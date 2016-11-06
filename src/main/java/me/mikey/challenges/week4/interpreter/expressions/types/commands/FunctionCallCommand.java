package me.mikey.challenges.week4.interpreter.expressions.types.commands;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.exceptions.BBExecutionException;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.types.BBCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.BBFunction;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 05/11/16.
 */
public class FunctionCallCommand extends BBCommand {
    public FunctionCallCommand(Token command, BBArgList argList) {
        super(command, argList);
    }

    @Override
    public void execute(BBVirtualMachine vm) throws BBExecutionException {
        BBFunction function = vm.getFunction(command.getData());

        if(function == null) {
            throw new BBExecutionException("Unknown function " + command.getData() + "!");
        }

        vm.executeFunction(command.getData(), argList);
    }
}
