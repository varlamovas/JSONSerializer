package com.varlamovas.jsonserializer;

public abstract class NewToken {

    private final String value;

    public NewToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
};