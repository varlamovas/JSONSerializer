package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class ShortAdapter implements ObjectAdapter<Short> {

    @Override
    public Short fromJson(Token token) {
        Short number = new Short(token.getValue());
        return number;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}