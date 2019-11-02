package com.varlamovas.jsonserializer.readers;

public interface CharacterReader {
    String readNext();
    String peekNext();
}
