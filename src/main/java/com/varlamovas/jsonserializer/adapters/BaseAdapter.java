package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public interface BaseAdapter{
//    <T> T fromJson(Token token);
    String toJson(Object value);

    default String toJson(Field objectField, Object instanceWithField) {
        return "???";
    }
}
