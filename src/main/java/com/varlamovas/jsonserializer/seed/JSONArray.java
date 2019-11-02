package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.tokens.Token;

public abstract class JSONArray extends BaseSeed {

    public abstract JSONObject createJSONObject();
    public abstract JSONArray createJSONArray();
    public abstract void addComb(BaseSeed seed);
    public abstract void add(Token token);
}
