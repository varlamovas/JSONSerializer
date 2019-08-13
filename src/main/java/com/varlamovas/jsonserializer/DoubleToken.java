package com.varlamovas.jsonserializer;

public class DoubleToken implements TokenInterface {

    private final Double value;

    public DoubleToken(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}
