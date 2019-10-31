package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class BooleanTypeAdapter implements ObjectAdapter<Boolean> {
    @Override
    public Boolean fromJson(Token token, Field field, Object instance) {
        Boolean bool = new Boolean(token.getValue());
//        FieldRetriever.setFieldObject(field, instance, bool);
        return bool;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
