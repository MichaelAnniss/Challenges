package me.mikey.challenges.week4.interpreter.expressions.types.commands;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.exceptions.BBException;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.expressions.types.BBCommand;
import me.mikey.challenges.week4.interpreter.expressions.types.math.MathExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVariable;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.Arrays;

/**
 * Created by Michael on 02/11/16.
 */
public class EqualsCommand extends BBCommand {
    private MathExpression exp;

    public EqualsCommand(Token curToken, MathExpression exp) {
        super(curToken, new BBArgList(Arrays.asList(curToken), Arrays.asList()));
        this.exp = exp;
    }

    public EqualsCommand(Token curToken, BBArgList list) {
        super(curToken, list);
    }

    @Override
    public void execute(BBVirtualMachine vm) {
        //Previous input is variable to assign to, normal input is value to assign from
        Token token = argList.getPreArgList().get(0);

        if(token.getType() != TokenType.VARIABLE)
            throw new UnsupportedOperationException("Can only assign values to variables! (Not " + token + ")");

        if(exp != null) {
            try {
                vm.setVariable(token.getData(), exp.executeExpression(vm));
            } catch (BBException e) {
                e.printStackTrace();
            }
        } else {
            BBVariable to = toVariable(token, vm);
            vm.setVariable(token.getData(), to.getValue());
        }
    }


    @Override
    public String toString() {
        return String.format("(BBCommand Equals %s)", argList);
    }


}
