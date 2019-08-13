package com.varlamovas.jsonserializer;

public class IntegerToken implements TokenInterface {

    private final Integer value;

    public IntegerToken(Integer value){
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
