package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public class BooleanTypeAdapter implements ObjectAdapter<Boolean> {
    @Override
    public Boolean fromJson(Token token) {
        Boolean bool = Boolean.valueOf(token.getValue());
        return bool;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
