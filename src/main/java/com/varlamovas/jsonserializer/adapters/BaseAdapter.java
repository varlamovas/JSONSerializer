package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public interface BaseAdapter<T> {
    T fromJson(Token token);
    String toJson(T value);
}
