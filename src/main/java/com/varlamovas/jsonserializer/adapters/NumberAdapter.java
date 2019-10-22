package com.varlamovas.jsonserializer.adapters;

public class NumberAdapter implements ObjectAdapter {
    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
