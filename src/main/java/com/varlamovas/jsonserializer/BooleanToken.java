package com.varlamovas.jsonserializer;

public enum BooleanToken implements TokenInterface {
    TRUE(true),
    FALSE(false);

    private final Boolean value;

    private BooleanToken(boolean value){
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
