package com.varlamovas.jsonserializer.tokens;

public class BooleanToken extends ValueToken {

    public static final ValueToken TRUE = new BooleanToken("true");
    public static final ValueToken FALSE = new BooleanToken("false");

    private BooleanToken(String value) {
        super(value);
    }
}
