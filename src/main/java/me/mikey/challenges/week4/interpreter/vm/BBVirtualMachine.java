package me.mikey.challenges.week4.interpreter.vm;

import me.mikey.challenges.week4.interpreter.InterpreterEventManager;
import me.mikey.challenges.week4.interpreter.expressions.types.BBBlock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 27/10/16.
 */
public class BBVirtualMachine {
    private Map<String, BBVariable> variables = new HashMap<>();
    private BBBlock block;

    public BBVirtualMachine(BBBlock block) {
        this.block = block;
    }

    public void execute() {
        block.execute(this);
    }

    public void shutdown() {
        for(Map.Entry<String, BBVariable> entry : variables.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public BBVariable getVariable(String name) {
        return variables.get(name);
    }

    public <T> T getVariableValue(String name, Class<T> clz) {
        return clz.cast(variables.get(name).getValue());
    }

    public void setVariable(String name, Object value) {
        Object oldValue = null;

        if(this.variables.containsKey(name)) {
            oldValue = this.variables.get(name).getValue();
        }

        BBVariable variable = new BBVariable<>(name, value);
        InterpreterEventManager.getInstance().variableUpdate(variable, oldValue, value, this);

        this.variables.put(name, variable);
    }
}
