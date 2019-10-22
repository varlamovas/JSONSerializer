package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.seed.ObjectSeed;
import com.varlamovas.jsonserializer.tokens.*;
import com.varlamovas.jsonserializer.tokens.ValueToken;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;

public class Parser<T> {

    private Lexer lexer;
    private List<Field> listOfFields;
    private ObjectSeed<T> objectSeed;

    public Parser(CharacterReader reader, ObjectSeed<T> objectSeed) {
        lexer = new Lexer(reader);
        this.objectSeed = objectSeed;
        this.listOfFields = objectSeed.getAllFields();
    }

    private void expect(Token token) {
        if (lexer.nextToken() != token) throw new IllegalArgumentException();
    }

    public void parseCommaSeparated(Token stopToken, Consumer<Token> body) {
        boolean expectComma = false;
        while (true) {
            Token token = lexer.nextToken();
            if (token == stopToken) return;
            if (expectComma) {
                if (token != MarkToken.COMMA) throw new MalformedJSONException("Expect comma");
                token = lexer.nextToken();
            }

            body.accept(token);

            expectComma = true;
        }
    }

    public void parseArrayBody(ObjectSeed objSeed, String propName) {
        parseCommaSeparated(MarkToken.RIGHT_SQUARE_BRACKET, (token) -> {
            parsePropertyValue(objSeed, propName, token);
        });
    }

    public void parsePropertyValue(ObjectSeed objectSeed, String propertyName, Token token) {
        if (token instanceof ValueToken) {
            objectSeed.addProperty(propertyName, token);
        }
//        if (token.equals(MarkToken.LEFT_SQUARE_BRACKET)) {
//            ObjectSeed newArrayObj = objectSeed.createNewArrayObject(propertyName);
//            parseArrayBody(newArrayObj, propertyName);
//        }
//        if (token.equals(MarkToken.LEFT_CURLY_BRACE)) {
//            ObjectSeed newObj = objectSeed.createNewObject(propertyName);
//            parseObjectBody(newObj);
//        }
    }

    public void parseObjectBody(ObjectSeed objectSeed) {
        parseCommaSeparated(MarkToken.RIGHT_CURLY_BRACE, (token) -> {
            if (!(token instanceof StringToken)) {
                throw new MalformedJSONException("unexpected Token");
            }
            String propName = token.getValue();
            expect(MarkToken.COLON);
            parsePropertyValue(objectSeed, propName, lexer.nextToken());
        });
    }

    public void parse() {
        expect(MarkToken.LEFT_CURLY_BRACE);
        parseObjectBody(objectSeed);
        if (lexer.nextToken() != null) throw new MalformedJSONException("Too many tokens");
    }
}

