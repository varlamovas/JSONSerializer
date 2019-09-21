package com.varlamovas.jsonserializer.tokens;

public enum BooleanToken implements TokenInterface {
    TRUE(true),
    FALSE(false);

    private final Boolean value;

    BooleanToken(boolean value){
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
