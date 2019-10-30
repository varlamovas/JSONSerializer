package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.seed.CollectionSeed;
import com.varlamovas.jsonserializer.seed.MapSeed;
import com.varlamovas.jsonserializer.seed.ObjectSeed;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public interface ObjectAdapter extends BaseAdapter {
    default void fromJson(Token token, Field field, Object instance) {};
    default void fromJson(Token token, CollectionSeed seed) {}
    default void fromJson(String property, Token token, MapSeed seed) {}
    String toJson(Object value);

}
