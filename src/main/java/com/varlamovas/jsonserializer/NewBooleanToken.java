package com.varlamovas.jsonserializer;

public class NewBooleanToken extends NewValueToken {

    static final NewValueToken TRUE = new NewBooleanToken("true");
    static final NewValueToken FALSE = new NewBooleanToken("false");

    private NewBooleanToken(String value) {
        super(value);
    }
}
