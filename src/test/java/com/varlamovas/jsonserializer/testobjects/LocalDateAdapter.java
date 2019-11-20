package com.varlamovas.jsonserializer.testobjects;

import com.varlamovas.jsonserializer.adapters.SimpleObjectAdapter;
import com.varlamovas.jsonserializer.tokens.Token;

import java.time.LocalDate;

public class LocalDateAdapter implements SimpleObjectAdapter {

    @Override
    public LocalDate fromJson(Token token) {
        return LocalDate.of(1994, 8, 15);
    }

    @Override
    public String toJson(Object value) {
        return "15.08.94";
    }
}
