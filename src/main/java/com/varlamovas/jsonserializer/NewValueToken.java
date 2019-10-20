package com.varlamovas.jsonserializer;

public class NewValueToken extends NewToken {

    public static final NewValueToken TRUE = new NewValueToken("true") {};
    public static final NewValueToken FALSE = new NewValueToken("false") {};
    public static final NewValueToken NULL = new NewValueToken("null") {};

    public NewValueToken(String value) {
        super(value);
    }
}
