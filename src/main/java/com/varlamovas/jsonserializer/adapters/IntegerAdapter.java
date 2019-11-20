package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public class IntegerAdapter implements ObjectAdapter<Integer> {

    @Override
    public Integer fromJson(Token token) {
        Integer number = new Integer(token.getValue());
        return number;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
