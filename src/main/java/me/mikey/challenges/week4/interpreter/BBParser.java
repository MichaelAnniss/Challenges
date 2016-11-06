package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.exceptions.*;
import me.mikey.challenges.week4.interpreter.expressions.BBArgList;
import me.mikey.challenges.week4.interpreter.expressions.BBExpression;
import me.mikey.challenges.week4.interpreter.expressions.types.*;
import me.mikey.challenges.week4.interpreter.expressions.types.commands.*;
import me.mikey.challenges.week4.interpreter.expressions.types.controls.BBWhile;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedInput;

import java.util.*;

/**
 * Created by Michael on 27/10/16.
 */
public class BBParser {
    private List<Token> tokens;

    private Stack<BBBlock> blockStack;
    private Map<String, BBFunction> functions;
    private BBBlock curBlock;

    public BBParser(List<Token> tokens) {
        this.tokens = tokens;
        this.blockStack = new Stack<>();
        this.functions = new HashMap<>();
        this.curBlock = null;
    }

    public BBParserResponse parse() throws BBException {
        ListIterator<Token> tokenIt = this.tokens.listIterator();

        while(tokenIt.hasNext()) {
            Token curToken = tokenIt.next();
            TokenType curTokenType = curToken.getType();

            if(curTokenType == TokenType.SEMICOLON)
                continue;

            List<Token> preInputs = new ArrayList<>();
            List<Token> inputs = new ArrayList<>();

            //only support simple pre-expected inputs
            if(curTokenType.preExpectedInputs().size() > 0)
                reverse(tokenIt, curTokenType.preExpectedInputs().size());

            for(ExpectedInput input : curTokenType.preExpectedInputs()) {
                if(input.evaluate(subList(this.tokens, tokenIt.nextIndex() - 1, this.tokens.size())).isMatched()) {
                    preInputs.add(tokens.get(tokenIt.nextIndex() - 1));
                    tokenIt.next();
                } else {
                    throw new UnexpectedTokenException("Unexpected pre-token " + this.tokens.get(tokenIt.nextIndex() - 1));
                }
            }

            BBExpression expression = null;

            for(ExpectedInput input : curTokenType.expectedInputs()) {
                ExpectedInput.ExpectedInputResponse response = input.evaluate(subList(this.tokens, tokenIt.nextIndex(), this.tokens.size()));

                if(response.isMatched()) {
                    if(response.getExpression() == null) {
                        inputs.addAll(response.getUsedTokens());
                    } else {
                        expression = response.getExpression();

                        //TODO this is hacky
                        if(expression instanceof EqualsCommand) {
                            ((EqualsCommand) expression).setArgList(new BBArgList(Arrays.asList(tokens.get(tokenIt.previousIndex() - 1)), Arrays.asList()));
                        }
                    }

                    advance(tokenIt, response.getUsedTokens().size());
                }
            }

            if(expression == null) {
                calcExpression(curToken, preInputs, inputs);
            } else {
                curBlock.addExpression(expression);
            }
        }

        if(blockStack.size() > 1) {
            throw new UnexpectedEOFException("Unexpected end of file! (Missing }?)");
        }

        return new BBParserResponse(blockStack.pop(), functions);
    }

    public void reverse(ListIterator<Token> it, int number) {
        if(number == 0)
            return;

        for(int i = 0; i < number; i++)
            it.previous();
    }

    public void advance(ListIterator<Token> it, int number) {
        if(number == 0)
            return;

        for(int i = 0; i < number; i++)
            it.next();
    }

    public List<Token> subList(List<Token> token, int start, int finish) {
        return new ArrayList<>(token.subList(start, finish));
    }

    public static BBParserResponse parse(List<Token> tokens) throws BBException {
        return new BBParser(tokens).parse();
    }

    public void calcExpression(Token curToken, List<Token> preInputs, List<Token> inputs) throws BBException {
        // Now to build the Expression
        TokenType curTokenType = curToken.getType();

        if(curTokenType.getExpressionType() == TokenType.ExpressionType.COMMAND) {
            if(curBlock == null) {
                throw new InvalidCommandException("Unknown block to add to? Something went very wrong");
            }

            if(curTokenType == TokenType.INCR) {
                curBlock.addExpression(new IncrCommand(curToken, new BBArgList(preInputs, inputs)));
            }

            else if(curTokenType == TokenType.DECR) {
                curBlock.addExpression(new DecrCommand(curToken, new BBArgList(preInputs, inputs)));
            }

            else if(curTokenType == TokenType.CLEAR) {
                curBlock.addExpression(new ClearCommand(curToken, new BBArgList(preInputs, inputs)));
            }

            else if(curTokenType == TokenType.EQUALS) {
                curBlock.addExpression(new EqualsCommand(curToken, new BBArgList(preInputs, inputs)));
            }

            else if (curTokenType == TokenType.FUNCTION_CALL) {
                curBlock.addExpression(new FunctionCallCommand(curToken, new BBArgList(preInputs, inputs)));
            }

            else {
                throw new InvalidCommandException("Unknown command type " + curTokenType + " at line " + curToken.getLineNumber());
            }
        }

        else if(curTokenType.getExpressionType() == TokenType.ExpressionType.BLOCK) {

            if(curTokenType == TokenType.FUNCTION) {
                if(blockStack.size() > 1) {
                    throw new InvalidFunctionException("Failed to define function " + curToken + " because " +
                            "functions can only exist in the root block!");
                }

                curBlock = new BBFunction(curToken.getData(), new BBArgList(preInputs, inputs));
                return;
            }

            BBControl control = new BBControl();

            if(curTokenType == TokenType.WHILE) {
                control = new BBWhile(new BBBoolean(inputs.subList(0, inputs.size() - 1)));
            }

            blockStack.add(new BBBlock(control));
            curBlock = blockStack.peek();
        }

        else if(curTokenType.getExpressionType() == TokenType.ExpressionType.BLOCKEND) {
            if(curBlock instanceof BBFunction) {
                BBFunction func = (BBFunction) curBlock;

                blockStack.peek().registerFunction(func);
                functions.put(func.getName(), func);
                curBlock = blockStack.peek();
            }

            else if(blockStack.size() > 1) {
                BBBlock lastStack = blockStack.pop();
                lastStack.addExpression(new BBEndBlock());
                blockStack.peek().addExpression(lastStack);
                curBlock = blockStack.peek();
            }
        }
    }


    public static class BBParserResponse {
        private BBBlock mainBlock;
        private Map<String, BBFunction> functions;

        public BBParserResponse(BBBlock mainBlock, Map<String, BBFunction> functions) {
            this.mainBlock = mainBlock;
            this.functions = functions;
        }

        public Map<String, BBFunction> getFunctions() {
            return functions;
        }

        public BBBlock getMainBlock() {
            return mainBlock;
        }
    }
}
