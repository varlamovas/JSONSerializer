package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.tokens.Token;

public abstract class BaseSeed {

    public abstract void addProperty(String propertyName, Token token);
    public abstract CollectionSeed createCollectionSeed(String name);
    public abstract void addCombProperty(String propertyName, BaseSeed seed);

    public abstract Object spawn();

//    public ObjectSeed createCollectionSeed(String propName) {
//        Class<?> clazz = getField(propName).getClass();
//        return new ObjectSeed(clazz);
//    }
//
//    public ObjectSeed createNewObject(String propName) {
//        Class<?> clazz = getField(propName).getClass();
//        return new ObjectSeed(clazz);
//    }
}
