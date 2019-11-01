package com.varlamovas.jsonserializer.seed;


import com.varlamovas.jsonserializer.tokens.Token;

public interface JSONObject extends BaseSeed {

    JSONObject createNewObject(String propertyName);

    JSONArray createCollectionSeed(String propertyName);

    void addCombProperty(String propertyName, BaseSeed newCollection);

    void addProperty(String propertyName, Token token);
}
