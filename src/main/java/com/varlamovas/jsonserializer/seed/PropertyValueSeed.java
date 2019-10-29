package com.varlamovas.jsonserializer.seed;


import com.varlamovas.jsonserializer.tokens.Token;

public interface PropertyValueSeed<T> extends BaseSeed<T> {

    PropertyValueSeed createNewObject(String propertyName);

    ArraySeed createCollectionSeed(String propertyName);

    void addCombProperty(String propertyName, BaseSeed newCollection);

    void addProperty(String propertyName, Token token);
}
