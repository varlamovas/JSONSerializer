package com.varlamovas.jsonserializer.adapters;

public class NullAdapter implements ObjectAdapter {

    @Override
    public String toJson(Object value) {
        return "null";
    }
}
