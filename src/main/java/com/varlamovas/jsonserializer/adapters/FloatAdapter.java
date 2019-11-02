package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class FloatAdapter implements ObjectAdapter<Float> {

    @Override
    public Float fromJson(Token token) {
        Float number = new Float(token.getValue());
        return number;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
