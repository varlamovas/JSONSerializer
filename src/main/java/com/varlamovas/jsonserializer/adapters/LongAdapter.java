package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;


public class LongAdapter implements ObjectAdapter<Long> {

    @Override
    public Long fromJson(Token token) {
        Long number = new Long(token.getValue());
        return number;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}