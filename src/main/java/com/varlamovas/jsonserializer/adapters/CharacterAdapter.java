package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.StringUtils;
import com.varlamovas.jsonserializer.tokens.Token;

public class CharacterAdapter implements ObjectAdapter<Character> {

    @Override
    public Character fromJson(Token token) {
        String str = token.getValue();
        assert str.length() == 1;
        Character ch = str.charAt(0);
        return ch;
    }

    @Override
    public String toJson(Object value) {
        return StringUtils.wrapByQuotes(value.toString());
    }
}
