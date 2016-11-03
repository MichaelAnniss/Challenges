package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.inputs.ExpectedCondition;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedInput;
import me.mikey.challenges.week4.interpreter.inputs.ExpectedInputOr;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Michael on 27/10/16.
 */
public enum TokenType {
    CLEAR("^(?i)clear") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    INCR("^(?i)incr") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    DECR("^(?i)decr") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    WHILE("^(?i)while") {
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
    SEMICOLON("^;") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },

    //Operators
    OP_NOT_EQUAL("^!=") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    OP_EQUALS("^==") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },

    DO("^(?i)do") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    END("^(?i)end") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCKEND;
        }
    },
    NUMBER("^[0-9]+") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    VARIABLE("^[a-zA-Z_][a-zA-Z0-9_]*") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    MAIN(null) {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCK;
        }
    },
    ENDMAIN(null) {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCKEND;
        }
    },

    /*
     * Additional Task Tokens
     */
    COMMENT("//(.*)?") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },

    EQUALS("=") {
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
    };

    private Pattern pattern;

    public List<ExpectedInput> preExpectedInputs() {
        return Arrays.asList();
    }

    public List<ExpectedInput> expectedInputs() {
        return Arrays.asList();
    }

    public abstract ExpressionType getExpressionType();

    TokenType(String regex) {
        if(regex != null)
            pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public enum ExpressionType {
        NONE,
        BLOCK,
        BLOCKEND,
        COMMAND
    }
}
