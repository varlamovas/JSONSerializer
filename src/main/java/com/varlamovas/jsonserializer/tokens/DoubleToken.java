package com.varlamovas.jsonserializer.tokens;

public class DoubleToken implements TokenInterface {

    private final Number value;

    public DoubleToken(Number value) {
        this.value = value;
    }

    @Override
    public Number getValue() {
        return value;
    }
}
