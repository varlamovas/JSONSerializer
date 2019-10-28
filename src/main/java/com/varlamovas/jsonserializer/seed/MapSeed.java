package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Type;

public class MapSeed<T> extends ObjectSeed<T> {

    public MapSeed(Class<T> clazz, Type type) {
        super(clazz, type);
    }

    public MapSeed(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void addProperty(String propertyName, Token token) {

    }

    @Override
    public CollectionSeed createCollectionSeed(String name) {
        return null;
    }

    @Override
    public void addCombProperty(String propertyName, BaseSeed seed) {

    }

    @Override
    public T spawn() {
        return null;
    }
}
