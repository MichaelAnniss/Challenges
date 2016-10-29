package me.mikey.challenges.week4.interpreter.expressions;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.vm.BBVariable;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

/**
 * Created by Michael on 27/10/16.
 */
public abstract class BBExpression {
    public BBVariable toVariable(Token token, BBVirtualMachine context) {
        if(token.getType() == TokenType.VARIABLE) {
            return context.getVariable(token.getData());
        }
        else if(token.getType() == TokenType.NUMBER) {
            return BBVariable.fromData(token.getData());
        }

        return null;
    }

    public abstract void execute(BBVirtualMachine vm);
}
