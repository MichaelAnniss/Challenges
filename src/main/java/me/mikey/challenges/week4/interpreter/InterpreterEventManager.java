package me.mikey.challenges.week4.interpreter;

import me.mikey.challenges.week4.interpreter.events.InterpreterListener;
import me.mikey.challenges.week4.interpreter.expressions.types.BBCommand;
import me.mikey.challenges.week4.interpreter.vm.BBVariable;
import me.mikey.challenges.week4.interpreter.vm.BBVirtualMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 31/10/16.
 */
public class InterpreterEventManager {
    private static InterpreterEventManager instance = new InterpreterEventManager();

    private List<InterpreterListener> listeners = new ArrayList<>();

    private InterpreterEventManager() {}

    public void registerListener(InterpreterListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(InterpreterListener listener) {
        listeners.remove(listener);
    }

    public void instructionExecuted(BBCommand cmd, BBVirtualMachine context) {
        for(InterpreterListener listener : listeners) {
            listener.instructionExecuted(cmd, context);
        }
    }

    public void variableUpdate(BBVariable variable, Object oldValue, Object newValue, BBVirtualMachine context) {
        for(InterpreterListener listener : listeners) {
            listener.variableUpdate(variable, oldValue, newValue, context);
        }
    }

    public static InterpreterEventManager getInstance() {
        return instance;
    }
}
