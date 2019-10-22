package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.StringUtils;

public class StringAdapter implements ObjectAdapter {


    StringAdapter() {}

//    @Override
//    public String fromJson(Token token) {
//        return token.getValue();
//    }

    @Override
    public String toJson(Object stringValue) {
        return StringUtils.wrapByQuotes(stringValue.toString());
    }

}
