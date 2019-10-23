package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.tokens.Token;

public class MapSeed extends BaseSeed {
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
    public Object spawn() {
        return null;
    }
}
