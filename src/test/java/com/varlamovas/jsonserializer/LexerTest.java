package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.tokens.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LexerTest {

    Lexer lexer;

    @BeforeEach
    void setUpTest() throws FileNotFoundException {
        String pathToFile = Paths.get("").resolve( "src/test/resources/Test.json").toAbsolutePath().toString();
        Reader fileReader = new FileReader(pathToFile);
        System.out.println(pathToFile);
        lexer = new Lexer(fileReader);
    }


    @Test
    void nextToken() {

        TokenInterface currentToken =  lexer.nextToken();
        assertEquals(Token.LEFT_CURLY_BRACE, currentToken);

        currentToken =  lexer.nextToken();
        assertEquals(StringToken.class, currentToken.getClass());
        assertEquals("test", currentToken.getValue());

        currentToken = lexer.nextToken();
        assertEquals(Token.COLON, currentToken);

        currentToken = lexer.nextToken();
        assertEquals(NumberToken.class, currentToken.getClass());
    }

    // "42 4.2 4e2 4e+2 4e-2 -3 0 0"
    private static Stream<Arguments> provideStringsForNumberTokenChecks(){
        return Stream.of(
                Arguments.of(" 42 ", NumberToken.class, 42),
                Arguments.of(" 4.2 ", DoubleToken.class, 4.2),
                Arguments.of(" 4e2 ", DoubleToken.class, 4e2),
                Arguments.of(" 4e+2 ", DoubleToken.class, 4e+2),
                Arguments.of(" 4e-2 ", DoubleToken.class, 4e-2),
                Arguments.of(" -3 ", NumberToken.class, -3),
                Arguments.of(" 0 ", NumberToken.class, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForNumberTokenChecks")
    void nextTokenReadNumberToken(String stringForReader, Class expectedToken, Number expectedNumber) {
        Reader stringReader = new StringReader(stringForReader);
        Lexer lexer = new Lexer(stringReader);
        TokenInterface currentToken = lexer.nextToken();
        assertEquals(expectedToken, currentToken.getClass());
        assertEquals(expectedNumber, currentToken.getValue());
    }

    @Test
    void nextTokenReadStringToken(){
        Reader stringReader = new StringReader("\"ha\" \"bla\" foo");
        Lexer lexer = new Lexer(stringReader);
        TokenInterface currentToken;

        currentToken = lexer.nextToken();
        assertEquals(StringToken.class, currentToken.getClass());
        assertEquals("ha", currentToken.getValue());

        currentToken = lexer.nextToken();
        assertEquals(StringToken.class, currentToken.getClass());
        assertEquals("bla", currentToken.getValue());

        assertThrows(MalformedJSONException.class, () -> lexer.nextToken());
    }
}