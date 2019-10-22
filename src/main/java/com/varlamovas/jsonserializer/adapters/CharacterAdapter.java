package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public class CharacterAdapter implements BaseAdapter {
//    @Override
//    public Object fromJson(Token token) {
//        return null;
//    }

    @Override
    public String toJson(Object value) {
        return "\"" + value.toString() + "\"";
    }
}
