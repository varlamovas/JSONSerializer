package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.seed.*;
import com.varlamovas.jsonserializer.tokens.*;
import com.varlamovas.jsonserializer.tokens.ValueToken;
import com.varlamovas.jsonserializer.tokens.Token;

import java.util.Collection;
import java.util.function.Consumer;

public class Parser<T> {

    private Lexer lexer;
    private ObjectSeed<T> objectSeed;

    public Parser(CharacterReader reader, ObjectSeed<T> objectSeed) {
        lexer = new Lexer(reader);
        this.objectSeed = objectSeed;
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

    public void parseArrayBody(ArraySeed objSeed, String propName) {
        parseCommaSeparated(MarkToken.RIGHT_SQUARE_BRACKET, (token) -> {
            parsePropertyValue(objSeed, propName, token);
        });
    }

    public void parsePropertyValue(BaseSeed seed, String propertyName, Token token) {
        if (token instanceof ValueToken) {
            if (seed.isPropertyValue()) {
                ((PropertyValueSeed) seed).addProperty(propertyName, token);
            } else if (seed.isCollection()) {
                ((ArraySeed) seed).add(token);
            }
        }
        if (token.equals(MarkToken.LEFT_SQUARE_BRACKET)) {
            if (seed.isPropertyValue()) {
                ArraySeed newCollection = ((PropertyValueSeed) seed).createCollectionSeed(propertyName);
                parseArrayBody(newCollection, propertyName);
                ((PropertyValueSeed) seed).addCombProperty(propertyName, newCollection);
            }
            else if (seed.isCollection()) {
                ArraySeed newCollection = ((ArraySeed) seed).createCollectionSeed();
                parseArrayBody(newCollection, propertyName);
                ((ArraySeed) seed).addComb(newCollection);
            }
        }
        if (token.equals(MarkToken.LEFT_CURLY_BRACE)) {
            if (seed.isPropertyValue()) {
                PropertyValueSeed newObj = ((PropertyValueSeed) seed).createNewObject(propertyName);
                parseObjectBody(newObj);
                ((PropertyValueSeed) seed).addCombProperty(propertyName, newObj);
            } else if (seed.isCollection()) {
                PropertyValueSeed newObj = ((ArraySeed) seed).createNewObject();
                parseObjectBody(newObj);
                ((ArraySeed) seed).addComb(newObj);
            }
        }
    }

    public void parseObjectBody(PropertyValueSeed objectSeed) {
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

