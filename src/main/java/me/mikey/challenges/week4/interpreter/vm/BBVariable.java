package me.mikey.challenges.week4.interpreter.vm;

import me.mikey.challenges.week4.interpreter.util.NumberUtil;

/**
 * Created by Michael on 27/10/16.
 */
public class BBVariable {
    private String name;
    private Object value;

    public BBVariable(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s = %s", name, value);
    }

    public static BBVariable fromData(String data) {
        if(NumberUtil.isNumber(data)) {
            return new BBVariable("@", Integer.parseInt(data));
        }

        return new BBVariable("@", null);
    }
}
