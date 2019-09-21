package com.varlamovas.jsonserializer.readers;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

public class CharactersReaderAdapter {

    public static CharacterReader getReader(Reader reader) {
        if (reader instanceof StringReader) {
            return new CharactersReaderNew((StringReader) reader);
        } else if (reader instanceof FileReader){
            return new CharactersReader(reader);
        }
        else return null;
    }
}
