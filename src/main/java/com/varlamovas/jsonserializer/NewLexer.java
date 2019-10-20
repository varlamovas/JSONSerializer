package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.tokens.DoubleToken;
import com.varlamovas.jsonserializer.tokens.NumberToken;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NewLexer {

    private CharacterReader reader;
    private Map<String, NewToken> tokenMap = new HashMap<String, NewToken>() {
        {
            put("{", NewMarkToken.LEFT_CURLY_BRACE);
            put("}", NewMarkToken.RIGHT_CURLY_BRACE);
            put("[", NewMarkToken.LEFT_SQUARE_BRACKET);
            put("]", NewMarkToken.RIGHT_SQUARE_BRACKET);
            put(":", NewMarkToken.COLON);
            put(",", NewMarkToken.COMMA);
        }
    };

    private Set<String> endedChars = new HashSet<String>() {
        {
            add(" ");
            add("\n");
            add("\t");
            add("\r");
            add("]");
            add("}");
            add(",");
        }
    };

    NewLexer(CharacterReader reader) {
        this.reader = reader;
    }

    public NewToken nextToken() {
        String character;
        do {
            character = reader.readNext();
        } while (StringUtils.isWhitespace(character));
        if (character == null) return null;
        if (tokenMap.containsKey(character)) return tokenMap.get(character);
        if (character.equals("\"")) return readStringToken();
        if (StringUtils.isNumber(character) || character.equals("-")) return readNumberToken(character);
        if (character.matches("[tf]")) return readBooleanToken(character);
        if (character.matches("n")) return readNullToken(character);
        return null;
    }

    public NewToken readNullToken(String initialCharacter) {
        String resultBool = readLexeme(initialCharacter).toString();
        if (resultBool.equals(NewNullToken.NULL.getValue())) {
            return NewNullToken.NULL;
        }
        throw new MalformedJSONException("invalid syntax");
    }

    public NewToken readBooleanToken(String character) {
        String resultBool = readLexeme(character).toString();
        if (resultBool.equals(NewBooleanToken.TRUE.getValue())) {
            return NewBooleanToken.TRUE;
        } else if (resultBool.equals(NewBooleanToken.FALSE.getValue())) {
            return NewBooleanToken.FALSE;
        }
        throw new MalformedJSONException("Invalid syntax");
    }

    public StringBuilder readLexeme(String initialCharacter) {
        StringBuilder resultString = new StringBuilder(initialCharacter);
        while (!endedChars.contains(reader.peekNext()) && reader.peekNext() != null) {
            resultString.append(reader.readNext());
        }
        return resultString;
    }

    public NewToken readNumberToken(String character) {
        String resultNumber = readLexeme(character).toString();
        if (resultNumber.startsWith("0") && resultNumber.length() > 1) {
            //TODO: think about it exception
            throw new MalformedJSONException("Invalid number");
        }
        if (resultNumber.matches("-?\\d*\\.\\d*[eE]?[+-]?\\d*")) {
            return new NewFloatToken(resultNumber);
        }
        if (resultNumber.matches("-?\\d+")) {
            return new NewIntegerToken(resultNumber);
        }
        // TODO: think about it exception
        throw new MalformedJSONException("invalid syntax");
    }

    public NewToken readStringToken() {
        StringBuilder resultString = new StringBuilder();
        String c;
        while (true) {
            c = reader.readNext();
            if (c == null) throw new MalformedJSONException("Unterminated string");
            if (c.equals("\"")) break;
            if (c.equals("\\")) {
                //TODO: implements unicode hex encoded string
            } else {
                resultString.append(c);
            }
        }
        return new NewStringToken(resultString.toString());
    }


}
