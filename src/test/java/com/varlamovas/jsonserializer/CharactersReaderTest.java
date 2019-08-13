package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.*;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class CharactersReaderTest {

    private static Reader reader;
    private static CharactersReader charactersReader;

    @BeforeAll
    static void setUpCLass(){
        System.out.println("Beffore all tests");
    }
    @AfterAll
    static void tearDownClass(){
        System.out.println("After all tests");
    }

    @BeforeEach
    void setUpTest() {
//        String pathToFile = Paths.get("").resolve( "src/test/resources/Test.json").toAbsolutePath().toString();
//        try {
//            reader = new FileReader(pathToFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        reader = new StringReader("{\n  \"test\": 42\n}");
        charactersReader = new CharactersReader(reader);
        System.out.println("Before each test");
    }

    @AfterEach
    void tearDownTest(){
        System.out.println("After each test");
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
    void readNextCharacters() {
        String c;
        c = charactersReader.readNextCharacters(4);
        assertEquals("{\n  ", c);
        c = charactersReader.readNextCharacters(4);
        assertEquals("\"tes", c);
        c = charactersReader.readNextCharacters(4);
        assertEquals("t\": ", c);
        c = charactersReader.readNextCharacters(2);
        assertEquals("42", c);

        assertThrows(MalformedJSONException.class, () -> charactersReader.readNextCharacters(3));

    }

    @Test
    void readNextAndReadNextCharacters() {
        assertEquals("{", charactersReader.readNext());
        assertEquals("\n  \"", charactersReader.readNextCharacters(4));
        assertEquals("t", charactersReader.readNext());
        assertEquals("est", charactersReader.readNextCharacters(3));
    }

    @Test
    void pick(){
        assertEquals("{", charactersReader.pickNext());
        assertEquals("{", charactersReader.pickNext());
        assertEquals("{", charactersReader.pickNext());
        assertEquals("{", charactersReader.pickNext());
    }
}
