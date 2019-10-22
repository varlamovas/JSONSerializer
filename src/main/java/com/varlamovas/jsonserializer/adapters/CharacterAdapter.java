package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.StringUtils;

public class CharacterAdapter implements ObjectAdapter {
//    @Override
//    public Object fromJson(Token token) {
//        return null;
//    }

    @Override
    public String toJson(Object value) {
        return StringUtils.wrapByQuotes(value.toString());
    }
}
