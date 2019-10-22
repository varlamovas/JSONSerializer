package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.StringUtils;

import java.lang.reflect.Field;

public class StringAdapter implements BaseAdapter {


    StringAdapter() {}

//    @Override
//    public String fromJson(Token token) {
//        return token.getValue();
//    }

    @Override
    public String toJson(Object stringValue) {
        return "\"" + stringValue.toString() + "\"";
    }


    @Override
    public String toJson(Field objectField, Object instanceWithField) {
        Object objectByField = null;
        String jsonRepr;
        try {
            objectByField = objectField.get(instanceWithField);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        jsonRepr = (String) objectByField;
        return StringUtils.wrapByQuotes(jsonRepr);
    }
}
