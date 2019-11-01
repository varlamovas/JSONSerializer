package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.tokens.Token;

public class ValueSeed implements BaseSeed {

    private final Token token;

    private ValueSeed(Token token) {
        this.token = token;
    }

    @Override
    public Object spawn() {
        return null;
    }

    public static ValueSeed ofToken(Token token) {
        ValueSeed instance = new ValueSeed(token);
        return instance;
    }


}
