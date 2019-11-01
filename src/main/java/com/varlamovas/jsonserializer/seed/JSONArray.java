package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.tokens.Token;

public interface JSONArray extends BaseSeed {
    JSONObject createNewObject();

    JSONArray createCollectionSeed();

    void addComb(BaseSeed seed);

    void add(Token token);
}
