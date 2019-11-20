package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.ReaderChars;
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
        ObjectSeed objectSeed = new ObjectSeed((Type) klass);

//        CharacterReader stringReader = new CharactersReaderSimple(new StringReader(json));
//        Parser parser = new Parser(stringReader, objectSeed);

        ReaderChars readerChars = new ReaderChars(new StringReader(json));
        Parser parser = new Parser(readerChars, objectSeed);

        parser.parse();
        @SuppressWarnings("unchecked") T resultObject = (T) objectSeed.spawn();
        return resultObject;
    }
}
