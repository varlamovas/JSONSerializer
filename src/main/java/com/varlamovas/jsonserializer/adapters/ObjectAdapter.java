package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public interface ObjectAdapter<T> extends BaseAdapter {
    default T fromJson(Token token) {return null;}
    String toJson(Object value);

}
