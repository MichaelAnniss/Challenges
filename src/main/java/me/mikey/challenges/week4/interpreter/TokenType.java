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
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },

    //Operators
    OP_NOT_EQUAL {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("!=");
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    OP_EQUALS {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("==");
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
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },

    EQUALS {
        @Override
        public boolean matches(String input) {
            return input.equalsIgnoreCase("=");
        }

        @Override
        public List<ExpectedInput> preExpectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE));
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInputOr(VARIABLE, NUMBER), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    }
    ;

    public abstract boolean matches(String input);

    public List<ExpectedInput> preExpectedInputs() {
        return Arrays.asList();
    }

    public List<ExpectedInput> expectedInputs() {
        return Arrays.asList();
    }

    public abstract ExpressionType getExpressionType();

    public enum ExpressionType {
        NONE,
        BLOCK,
        BLOCKEND,
        COMMAND
    }
}
