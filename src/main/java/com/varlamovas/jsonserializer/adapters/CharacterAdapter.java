package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.StringUtils;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class CharacterAdapter implements ObjectAdapter {

    @Override
    public void fromJson(Token token, Field field, Object instance) {
        String str = token.getValue();
        assert str.length() == 1;
        Character ch = str.charAt(0);
        FieldRetriever.setFieldObject(field, instance, ch);
    }

    @Override
    public String toJson(Object value) {
        return StringUtils.wrapByQuotes(value.toString());
    }
}
