package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public class DoubleAdapter implements ObjectAdapter<Double> {

    @Override
    public Double fromJson(Token token) {
        Double number = new Double(token.getValue());
        return number;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
