package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.readers.CharactersReaderSimple;
import com.varlamovas.jsonserializer.seed.ObjectSeed;

import java.io.StringReader;
import java.lang.reflect.Type;

public class Deserializer<T> {
    private String json;
    private Class<T> klass;

    public Deserializer(String json, Class<T> klass) {
        this.json = json;
        this.klass = klass;
    }

    public T deserialize() {
        CharacterReader stringReader = new CharactersReaderSimple(new StringReader(json));
        ObjectSeed objectSeed = new ObjectSeed((Type) klass);
        Parser parser = new Parser(stringReader, objectSeed);
        parser.parse();
        return (T) objectSeed.spawn();
    }
}
