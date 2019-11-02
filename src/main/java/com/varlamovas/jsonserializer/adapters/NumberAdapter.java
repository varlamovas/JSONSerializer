package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.tokens.FloatToken;
import com.varlamovas.jsonserializer.tokens.IntegerToken;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class NumberAdapter implements ObjectAdapter<Number> {

    @Override
    public Number fromJson(Token token) {
        Number number;
        if (token instanceof IntegerToken) {
            number = new Long(token.getValue());
            return number;
        } else if (token instanceof FloatToken) {
            number = new Double(token.getValue());
            return number;
        }
        throw new IllegalStateException();
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}
