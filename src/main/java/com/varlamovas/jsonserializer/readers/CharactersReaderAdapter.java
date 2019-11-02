package com.varlamovas.jsonserializer.readers;

import java.io.Reader;
import java.io.StringReader;

public class CharactersReaderAdapter {

    public static CharacterReader getReader(Reader reader) {
        if (reader instanceof StringReader) {
            return new CharactersReaderSimple((StringReader) reader);
//        } else if (reader instanceof FileReader){
//            return new CharactersReader(reader);
        }
        else return null;
    }
}
