package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public class PrimitiveTypeAdapter<T> implements BaseAdapter<T> {
    @Override
    public T fromJson(Token token) {
        return null;
    }

    @Override
    public String toJson(T value) {
        return null;
    }
}
