package me.mikey.challenges.week4.interpreter.expressions.types.commands;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.expressions.types.BBCommand;
import me.mikey.challenges.week4.interpreter.vm.BBVariable;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 02/11/16.
 */
public class EqualsCommand extends BBCommand {
    public EqualsCommand(Token curToken, BBArgList bbArgList) {
        super(curToken, bbArgList);
    }

    @Override
    public void execute(BBVirtualMachine vm) {
        //Previous input is variable to assign to, normal input is value to assign from
        Token token = argList.getPreArgList().get(0);

        if(token.getType() != TokenType.VARIABLE)
            throw new UnsupportedOperationException("Can only assign values to variables!");

        BBVariable to = toVariable(argList.getArgList().get(0), vm);

        vm.setVariable(token.getData(), to.getValue());
    }
}
