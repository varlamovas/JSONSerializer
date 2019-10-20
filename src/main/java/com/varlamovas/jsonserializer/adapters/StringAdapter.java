package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.NewToken;

public class StringAdapter implements BaseAdapter<String> {

    @Override
    public String fromJson(NewToken token) {
        return token.getValue();
    }

    @Override
    public String toJson(String value) {
        return value;
    }

}
