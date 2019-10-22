package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.tokens.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lexer {

    private CharacterReader reader;
    private Map<String, Token> tokenMap = new HashMap<String, Token>() {
        {
            put("{", MarkToken.LEFT_CURLY_BRACE);
            put("}", MarkToken.RIGHT_CURLY_BRACE);
            put("[", MarkToken.LEFT_SQUARE_BRACKET);
            put("]", MarkToken.RIGHT_SQUARE_BRACKET);
            put(":", MarkToken.COLON);
            put(",", MarkToken.COMMA);
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

    Lexer(CharacterReader reader) {
        this.reader = reader;
    }

    public Token nextToken() {
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

    public Token readNullToken(String initialCharacter) {
        String resultBool = readLexeme(initialCharacter).toString();
        if (resultBool.equals(NullToken.NULL.getValue())) {
            return NullToken.NULL;
        }
        throw new MalformedJSONException("invalid syntax");
    }

    public Token readBooleanToken(String character) {
        String resultBool = readLexeme(character).toString();
        if (resultBool.equals(BooleanToken.TRUE.getValue())) {
            return BooleanToken.TRUE;
        } else if (resultBool.equals(BooleanToken.FALSE.getValue())) {
            return BooleanToken.FALSE;
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

    public Token readNumberToken(String character) {
        String resultNumber = readLexeme(character).toString();
        if (resultNumber.startsWith("0") && resultNumber.length() > 1) {
            //TODO: think about it exception
            throw new MalformedJSONException("Invalid number");
        }
//        if (resultNumber.matches("-?\\d+(\\.\\d*)?[eE]?[+-]?\\d*")) {
//        if (resultNumber.matches("-?\\d+(\\.\\d*)?[eE]?[+-]?\\d*")) {
        if (resultNumber.matches("-?\\d+")) {
            return new IntegerToken(resultNumber);
        }
        if (resultNumber.matches("-?\\d+\\.\\d+") || resultNumber.matches("-?\\d+[eE][+-]?\\d+") || resultNumber.matches("-?\\d+(\\.\\d*)?[eE]?[+-]?\\d*")) {
            return new FloatToken(resultNumber);
        }
        // TODO: think about it exception
        throw new MalformedJSONException("invalid syntax");
    }

    public Token readStringToken() {
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
        return new StringToken(resultString.toString());
    }


}
