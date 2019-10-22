package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public class StringAdapter implements BaseAdapter<String> {

    @Override
    public String fromJson(Token token) {
        return token.getValue();
    }

    @Override
    public String toJson(String value) {
        return "\"" + value + "\"";
    }

}
