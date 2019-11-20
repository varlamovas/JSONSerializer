package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.CharactersReaderSimple;
import org.junit.jupiter.api.*;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharactersReaderSimpleTest {

    private static CharactersReaderSimple charactersReader;

    @BeforeEach
    void setUpTest() {
        StringReader reader = new StringReader("{\n  \"test\": 42\n}");
        charactersReader = new CharactersReaderSimple(reader);
        System.out.println("Before each test");
    }

    @Test
    void readNext() {
        assertEquals("{", charactersReader.readNext());
        assertEquals("\n", charactersReader.readNext());
        assertEquals(" ", charactersReader.readNext());
        assertEquals(" ", charactersReader.readNext());
        assertEquals("\"", charactersReader.readNext());
        assertEquals("t", charactersReader.readNext());
        assertEquals("e", charactersReader.readNext());
        assertEquals("s", charactersReader.readNext());
        assertEquals("t", charactersReader.readNext());

    }

    @Test
    void peekNext(){
        assertEquals("{", charactersReader.peekNext());
        assertEquals("{", charactersReader.peekNext());
        assertEquals("{", charactersReader.peekNext());
        assertEquals("{", charactersReader.peekNext());
    }

    @Test
    void readNext__and__peelNext() {
        assertEquals("{", charactersReader.readNext());
        assertEquals("\n", charactersReader.peekNext());
        assertEquals("\n", charactersReader.peekNext());
        assertEquals("\n", charactersReader.readNext());
        assertEquals(" ", charactersReader.readNext());
        assertEquals(" ", charactersReader.readNext());
        assertEquals("\"", charactersReader.peekNext());
        assertEquals("\"", charactersReader.peekNext());
        assertEquals("\"", charactersReader.readNext());
        assertEquals("t", charactersReader.readNext());
    }
}
