package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.seed.ObjectSeed;

import java.io.Reader;
import java.io.StringReader;

public class Deserializer<T> {

    private String json;
    private Class<T> klass;

    public Deserializer(String json, Class<T> klass) {
        this.json = json;
        this.klass = klass;
    }

    public T deserialize() {
        Reader stringReader = new StringReader(json);
        ObjectSeed<T> objectSeed = new ObjectSeed<>(klass);
        Parser<T> parser = new Parser<>(stringReader, objectSeed);
        return parser.parseCommaSeparated();
    }
}
