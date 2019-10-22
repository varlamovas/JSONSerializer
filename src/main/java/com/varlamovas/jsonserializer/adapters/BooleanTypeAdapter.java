package com.varlamovas.jsonserializer.adapters;

public class BooleanTypeAdapter implements ObjectAdapter {
//    @Override
//    public Object fromJson(Token token) {
//        return null;
//    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
