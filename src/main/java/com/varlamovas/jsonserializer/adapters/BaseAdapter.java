package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.NewToken;

public interface BaseAdapter<T> {
    T fromJson(NewToken token);
    String toJson(T value);
}
