package com.varlamovas.jsonserializer;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.List;

public class Deserializer<T> {

    private String json;
    private Class<T> klass;

    public Deserializer(String json, Class<T> klass) {
        this.json = json;
        this.klass = klass;
    }

    public T deserialize() {
        Reader stringReader = new StringReader(json);
        Seed<T> seed = new Seed<>(klass);
        Parser<T> parser = new Parser<>(stringReader, seed);
        return parser.parseCommaSeparated();
    }
}
