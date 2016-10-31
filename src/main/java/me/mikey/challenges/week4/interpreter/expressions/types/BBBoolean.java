package me.mikey.challenges.week4.interpreter.expressions.types;

import me.mikey.challenges.week4.interpreter.Token;
import me.mikey.challenges.week4.interpreter.TokenType;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.vm.BBVariable;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public class BBBoolean extends BBExpression {
   private List<Token> tokens;

   public BBBoolean(List<Token> tokens) {
      this.tokens = tokens;
   }

   public boolean isTrue(BBVirtualMachine context) {
      Token left = this.tokens.get(0);
      Token operator = this.tokens.get(1);
      Token right = this.tokens.get(2);

      BBVariable leftNumber = toVariable(left, context);
      BBVariable rightNumber = toVariable(right, context);

      if(operator.getType() == TokenType.NOT) {
         return !leftNumber.getValue().equals(rightNumber.getValue());
      }

      return false;
   }

   @Override
   public String toString() {
      String s = "(BBBoolean ";

      for(Token token : tokens) {
         s += token + " ";
      }

      s += ")";

      return s;
   }

   @Override
   public void execute(BBVirtualMachine vm) {
   }
}
