package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.readers.ReaderChars;
import com.varlamovas.jsonserializer.seed.JSONArray;
import com.varlamovas.jsonserializer.seed.JSONObject;
import com.varlamovas.jsonserializer.seed.ObjectSeed;
import com.varlamovas.jsonserializer.tokens.MarkToken;
import com.varlamovas.jsonserializer.tokens.StringToken;
import com.varlamovas.jsonserializer.tokens.Token;
import com.varlamovas.jsonserializer.tokens.ValueToken;

import java.util.function.Consumer;

class Parser {

    private Lexer lexer;
    private ObjectSeed objectSeed;

    Parser(ReaderChars reader, ObjectSeed objectSeed) {
        lexer = new Lexer(reader);
        this.objectSeed = objectSeed;
    }

    private void expect(Token token) {
        if (lexer.nextToken() != token) throw new IllegalArgumentException();
    }

    private void parseCommaSeparated(Token stopToken, Consumer<Token> body) {
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

    private void parseArrayBody(JSONArray objSeed) {
        parseCommaSeparated(MarkToken.RIGHT_SQUARE_BRACKET, (token) -> parseValue(objSeed, token));
    }

    private void parseValue(JSONArray seed, Token token) {
        if (token instanceof ValueToken) {
            seed.add(token);
        }
        if (token.equals(MarkToken.LEFT_SQUARE_BRACKET)) {
            JSONArray newCollection = seed.createJSONArray();
            parseArrayBody(newCollection);
            seed.addComb(newCollection);
        }
        if (token.equals(MarkToken.LEFT_CURLY_BRACE)) {
            JSONObject propertyValueSeed = seed.createJSONObject();
            parseObjectBody(propertyValueSeed);
            seed.addComb(propertyValueSeed);
        }
    }

    private void parsePropertyValue(JSONObject seed, String propertyName, Token token) {
        if (token instanceof ValueToken) {
            seed.addProperty(propertyName, token);
        }
        if (token.equals(MarkToken.LEFT_SQUARE_BRACKET)) {
            JSONArray newCollection = seed.createJSONArray(propertyName);
            parseArrayBody(newCollection);
            seed.addCombProperty(propertyName, newCollection);
        }
        if (token.equals(MarkToken.LEFT_CURLY_BRACE)) {
            JSONObject newObj = seed.createJSONObject(propertyName);
            parseObjectBody(newObj);
            seed.addCombProperty(propertyName, newObj);
        }
    }

    private void parseObjectBody(JSONObject objectSeed) {
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

