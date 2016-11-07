package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.inputs.*;

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
            return Arrays.asList(new ExpectedInput(VARIABLE),
                    new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    INCR("^(?i)incr") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE),
                    new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    DECR("^(?i)decr") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE),
                    new ExpectedInput(SEMICOLON));
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
                    new ExpectedInput(DO));
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

    DO("^(?i)[{]") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    END("^(?i)[}]") {
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
    FUNCTION("^function [a-zA-Z][a-zA-Z0-9]*") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(
                    new ExpectedInput(LPAREN),
                    new ExpectedArgListInput(),
                    new ExpectedInput(RPAREN)
            );
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.BLOCK;
        }
    },
    FUNCTION_CALL("^[a-zA-Z][a-zA-Z0-9]*[ ]?[(]") {
        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(
                    new ExpectedInput(LPAREN),
                    new ExpectedArgListInput(),
                    new ExpectedInput(RPAREN)
            );
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
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
    EQUALS("^=") {
        @Override
        public List<ExpectedInput> preExpectedInputs() {
            return Arrays.asList(new ExpectedInput(VARIABLE));
        }

        @Override
        public List<ExpectedInput> expectedInputs() {
            return Arrays.asList(new ExpectedEvaluableInput(), new ExpectedInput(SEMICOLON));
        }

        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.COMMAND;
        }
    },
    LPAREN("^[(]") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    RPAREN("^[)]") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    OP_PLUS("^[+]") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    OP_MINUS("^[-]") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    OP_MULTIPLY("^[*]") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    OP_DIVIDE("^[/]") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },
    COMMA("^,") {
        @Override
        public ExpressionType getExpressionType() {
            return ExpressionType.NONE;
        }
    },

    ;

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
