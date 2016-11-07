package me.mikey.challenges.week4.interpreter.expressions.types.commands;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.exceptions.BBExecutionException;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.types.BBCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.BBFunction;
import me.mikey.challenges.week4.interpreter.expressions.types.BBParamList;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 05/11/16.
 */
public class FunctionCallCommand extends BBCommand {
    private BBParamList paramList;

    public FunctionCallCommand(Token command, BBArgList argList, BBParamList paramList) {
        super(command, argList);
        this.paramList = paramList;
    }

    @Override
    public void execute(BBVirtualMachine vm) throws BBExecutionException {
        BBFunction function = vm.getFunction(command.getData());

        if(function == null) {
            throw new BBExecutionException("Unknown function " + command.getData() + "!");
        }

        try {
            vm.executeFunction(command.getData(), paramList);
        } catch (BBException e) {
            throw new BBExecutionException("Could not execute function " + command.getData() + " - " + e.getMessage());
        }
    }
}
