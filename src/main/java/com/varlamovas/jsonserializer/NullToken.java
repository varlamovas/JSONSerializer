package com.varlamovas.jsonserializer;

public enum NullToken implements TokenInterface {
    NULL;

    @Override
    public Object getValue() {
        return null;
    }
}
