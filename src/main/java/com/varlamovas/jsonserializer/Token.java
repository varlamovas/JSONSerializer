package com.varlamovas.jsonserializer;

public enum Token implements TokenInterface {
    LEFT_CURLY_BRACE,
    RIGHT_CURLY_BRACE,
    LEFT_SQUARE_BRACKET,
    RIGHT_SQUARE_BRACKET,
    COLON,
    COMMA,
    ;

    @Override
    public String getValue() {
        return null;
    }
}
