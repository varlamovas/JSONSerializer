package com.varlamovas.jsonserializer.tokens;

public class StringToken implements TokenInterface {

    private final String value;

    public StringToken(String value){
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}