package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.tokens.Token;

public interface ArraySeed<T> extends BaseSeed<T> {
    PropertyValueSeed createNewObject();

    ArraySeed createCollectionSeed();

    void addComb(BaseSeed seed);

    void add(Token token);
}
