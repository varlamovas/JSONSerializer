package com.varlamovas.jsonserializer;

public class NewNullToken extends NewValueToken {

    static final NewValueToken NULL = new NewNullToken("null");

    private NewNullToken(String value) {
        super(value);
    }
}
