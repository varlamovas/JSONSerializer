package com.varlamovas.jsonserializer.tokens;

public abstract class ValueToken extends Token {

    public static final ValueToken TRUE = new ValueToken("true") {};
    public static final ValueToken FALSE = new ValueToken("false") {};
    public static final ValueToken NULL = new ValueToken("null") {};

    protected ValueToken(String value) {
        super(value);
    }
}
