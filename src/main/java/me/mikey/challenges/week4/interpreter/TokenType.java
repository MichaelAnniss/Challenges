package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.inputs.ExpectedCondition;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedInput;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedInputOr;
import me.mikey.challenges.week4.interpreter.util.NumberUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 27/10/16.
 */
public enum TokenType {
    CLEAR {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("clear");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    INCR {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("incr");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    DECR {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("decr");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    WHILE {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("while");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(
                    new ExpectedInputOr(NUMBER, VARIABLE),
                    new ExpectedCondition(),
                    new ExpectedInputOr(NUMBER, VARIABLE),
                    new ExpectedInput(DO),
                    new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCK;
        }
    },
    SEMICOLON {
        @Override
        public boolean matches(String input) {
            return input.equals(";");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList();
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    NOT {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("not");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList();
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    DO {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("do");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    END {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("end");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCKEND;
        }
    },
    NUMBER {
        @Override
        public boolean matches(String input) {
            return NumberUtil.isNumber(input);
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList();
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    VARIABLE {
        @Override
        public boolean matches(String input) {
            return false;
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList();
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    MAIN {
        @Override
        public boolean matches(String input) {
            return false;
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList();
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCK;
        }
    },
    ENDMAIN {
        @Override
        public boolean matches(String input) {
            return false;
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList();
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCKEND;
        }
    },

    /*
     * Additional Task Tokens
     */
    COMMENT {
        @Override
        public boolean matches(String input) {
            return input.startsWith("//");
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList();
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    };

    public abstract boolean matches(String input);

    public abstract List<ExpectedInput> expectedInputs();

    public abstract ExpressionType getExpressionType();

    public enum ExpressionType {
        NONE,
        BLOCK,
        BLOCKEND,
        COMMAND
    }
}
