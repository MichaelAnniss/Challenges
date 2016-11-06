package me.mikey.challenges.week4.interpreter.vm;

import me.mikey.challenges.week4.interpreter.util.NumberUtil;

/**
 * Created by Michael on 27/10/16.
 */
public class BBVariable<T> {
    private String name;
    private T value;

    public BBVariable(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s = %s", name, value);
    }

    public static BBVariable fromData(String data) {
        if(NumberUtil.isNumber(data)) {
            return new BBVariable<>("@", Double.parseDouble(data));
        }

        return new BBVariable<Integer>("@", null);
    }
}
