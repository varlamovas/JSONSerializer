package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.ReaderChars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReaderCharsTest {

    private static ReaderChars charactersReader;

    @BeforeEach
    void setUpTest() {
        StringReader reader = new StringReader("{\n  \"test\": 42\n}");
        charactersReader = new ReaderChars(reader);
    }

    @Test
    void readNext() {
        assertEquals('{', (char) charactersReader.readNext());
        assertEquals('\n', (char) charactersReader.readNext());
        assertEquals(' ', (char) charactersReader.readNext());
        assertEquals(' ', (char) charactersReader.readNext());
        assertEquals('"', (char) charactersReader.readNext());
        assertEquals('t', (char) charactersReader.readNext());
        assertEquals('e', (char) charactersReader.readNext());
        assertEquals('s', (char) charactersReader.readNext());
        assertEquals('t', (char) charactersReader.readNext());

    }

    @Test
    void peekNext(){
        assertEquals('{', (char) charactersReader.peekNext());
        assertEquals('{', (char) charactersReader.peekNext());
        assertEquals('{', (char) charactersReader.peekNext());
        assertEquals('{', (char) charactersReader.peekNext());
    }

    @Test
    void readNext__and__peelNext() {
        assertEquals('{', (char) charactersReader.readNext());
        assertEquals('\n', (char) charactersReader.peekNext());
        assertEquals('\n', (char) charactersReader.peekNext());
        assertEquals('\n', (char) charactersReader.readNext());
        assertEquals(' ', (char) charactersReader.readNext());
        assertEquals(' ', (char) charactersReader.readNext());
        assertEquals('"', (char) charactersReader.peekNext());
        assertEquals('"', (char) charactersReader.peekNext());
        assertEquals('"', (char) charactersReader.readNext());
        assertEquals('t', (char) charactersReader.readNext());
    }

    @Test
    void test_of_end__readNext() {
        StringReader stringReader = new StringReader("0");
        ReaderChars charactersReader = new ReaderChars(stringReader);
        int i = charactersReader.readNext();
        i = charactersReader.readNext();
        i = charactersReader.readNext();
        assertEquals(-1, i);
    }

    @Test
    void test_of_end__peekNext() {
        StringReader stringReader = new StringReader("a");
        ReaderChars readerChars = new ReaderChars(stringReader);
        int i;
        i = readerChars.readNext();
        i = readerChars.peekNext();
        i = readerChars.peekNext();
        assertEquals(-1, i);
    }
}
