package com.varlamovas.jsonserializer.adapters;

public class NullAdapter implements ObjectAdapter {
//    @Override
//    public Object fromJson(Token token) {
//        return null;
//    }

    @Override
    public String toJson(Object value) {
        return "null";
    }
}
