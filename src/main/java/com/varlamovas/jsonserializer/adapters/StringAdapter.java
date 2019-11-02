package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.StringUtils;
import com.varlamovas.jsonserializer.seed.CollectionSeed;
import com.varlamovas.jsonserializer.seed.MapSeed;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;


public class StringAdapter implements ObjectAdapter {



    @Override
    public String fromJson(Token token) {
        return token.getValue();
    }


    @Override
    public String toJson(Object stringValue) {
        return StringUtils.wrapByQuotes(stringValue.toString());
    }

}
