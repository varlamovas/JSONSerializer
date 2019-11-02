package com.varlamovas.jsonserializer.tokens;

public class NullToken extends ValueToken {

    public static final ValueToken NULL = new NullToken("null");

    private NullToken(String value) {
        super(value);
    }
}
