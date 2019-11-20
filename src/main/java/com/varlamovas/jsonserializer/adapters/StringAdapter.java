package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.StringUtils;
import com.varlamovas.jsonserializer.tokens.Token;


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
