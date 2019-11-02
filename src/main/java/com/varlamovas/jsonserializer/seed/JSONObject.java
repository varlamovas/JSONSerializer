package com.varlamovas.jsonserializer.seed;


import com.varlamovas.jsonserializer.tokens.Token;

public abstract class JSONObject extends BaseSeed {
    public abstract JSONObject createJSONObject(String propertyName);
    public abstract JSONArray createJSONArray(String propertyName);
    public abstract void addCombProperty(String propertyName, BaseSeed newCollection);
    public abstract void addProperty(String propertyName, Token token);
}
