package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.readers.CharactersReaderNew;
import com.varlamovas.jsonserializer.seed.ObjectSeed;

import java.io.StringReader;

public class NewDeserializer<T> {
    private String json;
    private Class<T> klass;

    public NewDeserializer(String json, Class<T> klass) {
        this.json = json;
        this.klass = klass;
    }

    public T deserialize() {
        CharacterReader stringReader = new CharactersReaderNew(new StringReader(json));
        ObjectSeed<T> objectSeed = new ObjectSeed<>(klass);
        NewParser<T> parser = new NewParser<>(stringReader, objectSeed);
        parser.parse();
        return objectSeed.spawn();
    }
}
