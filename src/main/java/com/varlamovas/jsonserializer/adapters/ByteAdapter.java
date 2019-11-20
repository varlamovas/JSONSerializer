package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.tokens.Token;

public class ByteAdapter implements ObjectAdapter<Byte> {

    @Override
    public Byte fromJson(Token token) {
        Byte number = new Byte(token.getValue());
        return number;
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
