package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.readers.CharacterReader;
import com.varlamovas.jsonserializer.readers.CharactersReaderAdapter;
import com.varlamovas.jsonserializer.readers.CharactersReaderNew;
import com.varlamovas.jsonserializer.tokens.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Reader;
import java.io.StringReader;
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

    private CharacterReader charactersReader;
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
        charactersReader = CharactersReaderAdapter.getReader(reader);
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

    private TokenInterface validateNumberTokenFromString(String strNumber) {
        if (strNumber.startsWith("0") && strNumber.length() > 1) {
            throw new MalformedJSONException("Invalid number");
        }
        if (!NumberUtils.isCreatable(strNumber)) {
            throw new MalformedJSONException("Invalid number");
        }
        if (strNumber.matches("-?\\d*\\.\\d*[eE]?[+-]?\\d*")) {
            Number number = NumberUtils.createNumber(strNumber);
            if (number instanceof Float) {
                number = number.floatValue();
            } else if (number instanceof Double) {
                number = number.doubleValue();
            }
            return new DoubleToken(number);
        }
        if (strNumber.matches("-?\\d+")) {
            Number number;
            number = NumberUtils.createNumber(strNumber);
            if (number instanceof Long) {
                number = number.longValue();
            }
            else if (Byte.MIN_VALUE <= number.intValue() && number.intValue() <= Byte.MAX_VALUE) {
                number = number.byteValue();
            }
            else if (Short.MIN_VALUE <= number.intValue() && number.intValue() <= Short.MAX_VALUE) {
                number = number.shortValue();
            }

            return new NumberToken(number);
        }
        return null;
    }

    private TokenInterface readNumberToken(String firstCharacter) {
        StringBuilder resultNumber = new StringBuilder(firstCharacter);
        while (!endedChars.contains(charactersReader.peekNext()) || charactersReader.peekNext() == null) {
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
        while (!endedChars.contains(charactersReader.peekNext())) {
            if (charactersReader.peekNext() == null) {
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
        while (!endedChars.contains(charactersReader.peekNext()) || charactersReader.peekNext() == null) {
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
