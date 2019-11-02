package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class BooleanTypeAdapter implements ObjectAdapter<Boolean> {
    @Override
    public Boolean fromJson(Token token) {
        Boolean bool = new Boolean(token.getValue());
        return bool;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
