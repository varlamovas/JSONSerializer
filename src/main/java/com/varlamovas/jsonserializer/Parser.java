package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.seed.BaseSeed;
import com.varlamovas.jsonserializer.seed.CollectionSeed;
import com.varlamovas.jsonserializer.seed.ObjectSeed;
import com.varlamovas.jsonserializer.tokens.*;
import com.varlamovas.jsonserializer.tokens.ValueToken;
import com.varlamovas.jsonserializer.tokens.Token;

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

    public void parseArrayBody(CollectionSeed objSeed, String propName) {
        parseCommaSeparated(MarkToken.RIGHT_SQUARE_BRACKET, (token) -> {
            parsePropertyValue(objSeed, propName, token);
        });
    }

    public void parsePropertyValue(BaseSeed seed, String propertyName, Token token) {
        if (token instanceof ValueToken) {
            if (seed instanceof ObjectSeed) {
                seed.addProperty(propertyName, token);
            } else if (seed instanceof CollectionSeed) {
                ((CollectionSeed) seed).add(token);
            }
        }
        if (token.equals(MarkToken.LEFT_SQUARE_BRACKET)) {
            CollectionSeed collectionSeed = seed.createCollectionSeed(propertyName);
            parseArrayBody(collectionSeed, propertyName);
            seed.addCombProperty(propertyName, collectionSeed);
        }
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

