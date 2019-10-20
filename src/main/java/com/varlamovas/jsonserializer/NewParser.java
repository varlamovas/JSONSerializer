package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.seed.ObjectSeed;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;

public class NewParser<T> {

    private NewLexer lexer;
    private List<Field> listOfFields;
    private ObjectSeed<T> objectSeed;

    NewParser(CharacterReader reader, ObjectSeed<T> objectSeed) {
        lexer = new NewLexer(reader);
        this.objectSeed = objectSeed;
        this.listOfFields = objectSeed.getAllFields();
    }

    private void expect(NewToken token) {
        if (lexer.nextToken() != token) throw new IllegalArgumentException();
    }

    public void parseCommaSeparated(NewToken stopToken, Consumer<NewToken> body) {
        boolean expectComma = false;
        while (true) {
            NewToken token = lexer.nextToken();
            if (token == stopToken) return;
            if (expectComma) {
                if (token != NewMarkToken.COMMA) throw new MalformedJSONException("Expect comma");
                token = lexer.nextToken();
            }

            body.accept(token);

            expectComma = true;
        }
    }

    public void parseArrayBody(ObjectSeed objSeed, String propName) {
        parseCommaSeparated(NewMarkToken.RIGHT_SQUARE_BRACKET, (token) -> {
            parsePropertyValue(objSeed, propName, token);
        });
    }

    public void parsePropertyValue(ObjectSeed objectSeed, String propertyName, NewToken token) {
        if (token instanceof NewValueToken) {
            objectSeed.addProperty(propertyName, token);
        }
//        if (token.equals(NewMarkToken.LEFT_SQUARE_BRACKET)) {
//            ObjectSeed newArrayObj = objectSeed.createNewArrayObject(propertyName);
//            parseArrayBody(newArrayObj, propertyName);
//        }
//        if (token.equals(NewMarkToken.LEFT_CURLY_BRACE)) {
//            ObjectSeed newObj = objectSeed.createNewObject(propertyName);
//            parseObjectBody(newObj);
//        }
    }

    public void parseObjectBody(ObjectSeed objectSeed) {
        parseCommaSeparated(NewMarkToken.RIGHT_CURLY_BRACE, (token) -> {
            if (!(token instanceof NewStringToken)) {
                throw new MalformedJSONException("unexpected Token");
            }
            String propName = token.getValue();
            expect(NewMarkToken.COLON);
            parsePropertyValue(objectSeed, propName, lexer.nextToken());
        });
    }

    public void parse() {
        expect(NewMarkToken.LEFT_CURLY_BRACE);
        parseObjectBody(objectSeed);
        if (lexer.nextToken() != null) throw new MalformedJSONException("Too many tokens");
    }
}

