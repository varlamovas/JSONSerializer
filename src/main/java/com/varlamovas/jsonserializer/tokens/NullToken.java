package com.varlamovas.jsonserializer.tokens;

public enum NullToken implements TokenInterface {
    NULL;

    @Override
    public Object getValue() {
        return null;
    }
}
