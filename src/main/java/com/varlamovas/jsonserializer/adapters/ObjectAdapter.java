package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.seed.CollectionSeed;
import com.varlamovas.jsonserializer.seed.MapSeed;
import com.varlamovas.jsonserializer.seed.ObjectSeed;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public interface ObjectAdapter<T> extends BaseAdapter {
    default T fromJson(Token token, Field field, Object instance) {return null;}
    default T fromJson(Token token, CollectionSeed seed) {return null;}
    default T fromJson(String property, Token token, MapSeed seed) {return null;}
    String toJson(Object value);

}
