package com.varlamovas.jsonserializer.tokens;

public abstract class Token {

    private final String value;

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}