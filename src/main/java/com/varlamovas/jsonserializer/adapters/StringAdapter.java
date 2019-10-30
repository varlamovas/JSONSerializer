package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.StringUtils;
import com.varlamovas.jsonserializer.seed.ArraySeed;
import com.varlamovas.jsonserializer.seed.CollectionSeed;
import com.varlamovas.jsonserializer.seed.MapSeed;
import com.varlamovas.jsonserializer.seed.ObjectSeed;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;


public class StringAdapter implements ObjectAdapter {


    StringAdapter() {}

//    @Override
//    public String fromJson(Token token) {
//        return token.getValue();
//    }


    @Override
    public void fromJson(Token token, Field field, Object instance) {
        FieldRetriever.setFieldObject(field, instance, token.getValue());
    }

    @Override
    public void fromJson(Token token, CollectionSeed seed) {
        Collection<String> collection = seed.getInstance();
        String value = token.getValue();
        collection.add(value);
    }

    @Override
    public void fromJson(String property, Token token, MapSeed seed) {
        Map<String, String> map = seed.getInstance();
        map.put(property, token.getValue());
    }

    @Override
    public String toJson(Object stringValue) {
        return StringUtils.wrapByQuotes(stringValue.toString());
    }

}
