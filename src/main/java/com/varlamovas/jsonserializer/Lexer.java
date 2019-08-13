package com.varlamovas.jsonserializer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Reader;
import java.util.*;

public class Lexer {

    private Map<String, Token> tokenMap = new HashMap<String, Token>() {
        {
            put("{", Token.LEFT_CURLY_BRACE);
            put("}", Token.RIGHT_CURLY_BRACE);
            put("[", Token.LEFT_SQUARE_BRACKET);
            put("]", Token.RIGHT_SQUARE_BRACKET);
            put(":", Token.COLON);
            put(",", Token.COMMA);
        }
    };

    private CharactersReader charactersReader;
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

    public Lexer(Reader reader) {
        charactersReader = new CharactersReader(reader);
    }

    private TokenInterface readStringToken() {
        StringBuilder resultString = new StringBuilder();
        String c;
        while (true) {
            c = charactersReader.readNext();
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

    private TokenInterface validateNumberTokenFromString(String number) {
        if (number.startsWith("0") && number.length() > 1) {
            throw new MalformedJSONException("Invalid number");
        }
        if (!NumberUtils.isCreatable(number)) {
            throw new MalformedJSONException("Invalid number");
        }
        if (number.matches("-?\\d*[\\.eE][+-]?\\d+")) {
            Double doubleNumber = NumberUtils.createDouble(number);
            return new DoubleToken(doubleNumber);
        }
        if (number.matches("-?\\d+")) {
            Integer integerNumber = NumberUtils.createInteger(number);
            return new IntegerToken(integerNumber);
        }
        return null;
    }

    private TokenInterface readNumberToken(String firstCharacter) {
        StringBuilder resultNumber = new StringBuilder(firstCharacter);
        while (!endedChars.contains(charactersReader.pickNext()) || charactersReader.pickNext() == null) {
            resultNumber.append(charactersReader.readNext());
        }
        return validateNumberTokenFromString(resultNumber.toString());
    }

    private TokenInterface validateBooleanToken(String bool) {
        if (bool == null) {
            throw new MalformedJSONException("Invalid syntax");
        }
        if (bool.matches("true")) {
            return BooleanToken.TRUE;
        } else if (bool.matches("false")){
            return BooleanToken.FALSE;
        }
        throw new MalformedJSONException("Invalid syntax");
    }

    private TokenInterface readBooleanToken(String firstCharacter) {
        StringBuilder resultBoolean = new StringBuilder(firstCharacter);
        while (!endedChars.contains(charactersReader.pickNext())) {
            if (charactersReader.pickNext() == null) {
                break;
            }
            resultBoolean.append(charactersReader.readNext());
        }
        return validateBooleanToken(resultBoolean.toString());
    }

    private TokenInterface validateNullToken(String nul) {
        if (nul.matches("null")) {
            return NullToken.NULL;
        }
        throw new MalformedJSONException("Invalid syntax");
    }

    private TokenInterface readNullToken(String firstCharacter) {
        StringBuilder resultNull = new StringBuilder(firstCharacter);
        while (!endedChars.contains(charactersReader.pickNext()) || charactersReader.pickNext() == null) {
            resultNull.append(charactersReader.readNext());
        }
        return validateNullToken(resultNull.toString());
    }

    public TokenInterface nextToken() {
        String character;
        do {
            character = charactersReader.readNext();
        } while (StringUtils.isWhitespace(character));
        if (character == null) return null;
        if (tokenMap.containsKey(character)) return tokenMap.get(character);
        if (character.equals("\"")) return readStringToken();
        if (StringUtils.isNumeric(character) || character.equals("-")) return readNumberToken(character);
        if (character.matches("[tf]")) return readBooleanToken(character);
        if (character.matches("n")) return readNullToken(character);
        return null;
    }

}
