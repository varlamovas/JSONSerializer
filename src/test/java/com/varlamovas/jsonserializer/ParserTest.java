package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import com.varlamovas.jsonserializer.readers.ReaderChars;
import com.varlamovas.jsonserializer.seed.ObjectSeed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    static JsonSerializer jsonSerializer;
    static Gson gsonSerializer;

    @BeforeEach
    void setUpTest() {
        jsonSerializer = new JsonSerializer();
        gsonSerializer = new Gson();
    }
    @Test
    void parseCommaSeparated() {
        String json = "{\"stringField\":\"stringFieldValue\",\"byteFieldBoxed\":127,\"shortFieldBoxed\":32767,\"charFieldBoxed\":\"c\",\"intFieldBoxed\":2147483647,\"longFieldBoxed\":9223372036854775807,\"floatFieldBoxed\":3.4E38,\"doubleFieldBoxed\":1.7E308,\"booleanFieldBoxed\":true}";
        StringReader stringReader = new StringReader(json);
//        CharactersReaderSimple charReader = new CharactersReaderSimple(stringReader);
        ReaderChars charReader = new ReaderChars(stringReader);
        CustomClassForSimpleParserTest initial = CustomClassForSimpleParserTest.getInstance();
        ObjectSeed objectSeed = new ObjectSeed(CustomClassForSimpleParserTest.class);
//        Parser parser = new Parser(charReader, objectSeed);
        Parser parser = new Parser(charReader, objectSeed);
        parser.parse();
        CustomClassForSimpleParserTest deserialized = (CustomClassForSimpleParserTest) objectSeed.spawn();
        assertEquals(initial.stringField, deserialized.stringField);
        assertEquals(initial.byteFieldBoxed, deserialized.byteFieldBoxed);
        assertEquals(initial.shortFieldBoxed, deserialized.shortFieldBoxed);
        assertEquals(initial.charFieldBoxed, deserialized.charFieldBoxed);
        assertEquals(initial.intFieldBoxed, deserialized.intFieldBoxed);
        assertEquals(initial.longFieldBoxed, deserialized.longFieldBoxed);
        assertEquals(initial.floatFieldBoxed, deserialized.floatFieldBoxed);
        assertEquals(initial.doubleFieldBoxed, deserialized.doubleFieldBoxed);
        assertEquals(initial.booleanFieldBoxed, deserialized.booleanFieldBoxed);
    }

    @Test
    void parseCommaSeparated_classWithListOfStrings() {
    }
}

class CustomClassWithList {

    List<String> listOfStrings;
    public void setListOfString() {
        listOfStrings = Arrays.asList("list", "Of", "Strings");
    }
    public static CustomClassWithList getInstance() {
        CustomClassWithList instance = new CustomClassWithList();
        instance.setListOfString();
        return instance;
    }
}

class CustomClassForSimpleParserTest {

    String stringField;
    Byte byteFieldBoxed;
    Short shortFieldBoxed;
    Character charFieldBoxed;
    Integer intFieldBoxed;
    Long longFieldBoxed;
    Float floatFieldBoxed;
    Double doubleFieldBoxed;
    Boolean booleanFieldBoxed;

    public void setAllFIelds() {
        stringField = "stringFieldValue";
        byteFieldBoxed = 127;
        shortFieldBoxed = 32767;
        charFieldBoxed = 'c';
        intFieldBoxed = 2147483647;
        longFieldBoxed = 9223372036854775807L;
        floatFieldBoxed = 3.4e+38f;
        doubleFieldBoxed = 1.7e+308;
        booleanFieldBoxed = true;
    }

    public static CustomClassForSimpleParserTest getInstance() {
        CustomClassForSimpleParserTest instance = new CustomClassForSimpleParserTest();
        instance.setAllFIelds();
        return instance;
    }
}
