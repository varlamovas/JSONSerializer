package com.varlamovas.jsonserializer.tokens;

public class NumberToken implements TokenInterface {

    private final Number value;

    public NumberToken(Number value){
        this.value = value;
    }

    @Override
    public Number getValue() {
        return value;
    }
}
