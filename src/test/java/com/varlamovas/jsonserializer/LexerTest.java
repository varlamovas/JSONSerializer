package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.readers.ReaderChars;
import com.varlamovas.jsonserializer.tokens.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.StringReader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LexerTest {

    Lexer lexer;

    @BeforeEach
    void setUpTest() {
        StringReader stringReader = new StringReader("{\n  \"test\": 42\n}");
        ReaderChars readerChars = new ReaderChars(stringReader);
        lexer = new Lexer(readerChars);
    }


    @Test
    void nextToken() {

        Token currentToken = lexer.nextToken();
        assertEquals(MarkToken.LEFT_CURLY_BRACE, currentToken);

        currentToken = lexer.nextToken();
        assertEquals(StringToken.class, currentToken.getClass());
        assertEquals("test", currentToken.getValue());

        currentToken = lexer.nextToken();
        assertEquals(MarkToken.COLON, currentToken);

        currentToken = lexer.nextToken();
        assertEquals(IntegerToken.class, currentToken.getClass());
    }

    // "42 4.2 4e2 4e+2 4e-2 -3 0 0"
    private static Stream<Arguments> provideStringsForNumberTokenChecks() {
        return Stream.of(
                Arguments.of(" 42 ", IntegerToken.class),
                Arguments.of(" 4.2 ", FloatToken.class),
                Arguments.of(" 4e2 ", FloatToken.class),
                Arguments.of(" 4e+2 ", FloatToken.class),
                Arguments.of(" 4e-2 ", FloatToken.class),
                Arguments.of(" -3 ", IntegerToken.class),
                Arguments.of(" 0 ", IntegerToken.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForNumberTokenChecks")
    void nextTokenReadNumberToken(String stringForReader, Class expectedToken) {
        StringReader stringReader = new StringReader(stringForReader);
        ReaderChars readerChars = new ReaderChars(stringReader);
        Lexer lexer = new Lexer(readerChars);
        Token currentToken = lexer.nextToken();
        assertEquals(expectedToken, currentToken.getClass());
    }

    @Test
    void nextTokenReadStringToken() {
        StringReader stringReader = new StringReader("\"ha\" \"bla\" foo");
        ReaderChars readerChars = new ReaderChars(stringReader);
        Lexer lexer = new Lexer(readerChars);
        Token currentToken;

        currentToken = lexer.nextToken();
        assertEquals(StringToken.class, currentToken.getClass());
        assertEquals("ha", currentToken.getValue());

        currentToken = lexer.nextToken();
        assertEquals(StringToken.class, currentToken.getClass());
        assertEquals("bla", currentToken.getValue());

        assertThrows(MalformedJSONException.class, lexer::nextToken);
    }
}