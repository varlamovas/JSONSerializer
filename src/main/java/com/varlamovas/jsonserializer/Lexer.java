package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.readers.ReaderChars;
import com.varlamovas.jsonserializer.tokens.*;

public class Lexer {

    private ReaderChars reader;

    private int[] tokens = {123, 125, 91, 93, 58, 44};
    private int[] endedCharsArray = {32, 10, 9, 13, 125, 93, 44};

    boolean isEndedChars(int element) {
        for (int character : endedCharsArray) {
            if (character == element) return true;
        }
        return false;
    }

    boolean isToken(int element) {
        for (int token : tokens) {
            if (token == element) return true;
        }
        return false;
    }

    Token getMarkToken(int element) {
        switch (element) {
            case 123:
                return MarkToken.LEFT_CURLY_BRACE;
            case 125:
                return MarkToken.RIGHT_CURLY_BRACE;
            case 91:
                return MarkToken.LEFT_SQUARE_BRACKET;
            case 93:
                return MarkToken.RIGHT_SQUARE_BRACKET;
            case 58:
                return MarkToken.COLON;
            case 44:
                return MarkToken.COMMA;

        }
        throw new IllegalStateException();
    }

    Lexer(ReaderChars reader) {
        this.reader = reader;
    }

    public Token nextToken() {
        int character;
        do {
            character = reader.readNext();
            if (character == -1) return null;
        } while (Character.isWhitespace(character));
//        if (character == null) return null;


//        if (tokenMap.containsKey(character)) return tokenMap.get(character);
        if (isToken(character)) return getMarkToken(character);
//        if (character.equals("\"")) return readStringToken();
        if (character == 34 ) return readStringToken();
        if (Character.isDigit(character) || character == 45) return readNumberToken(character);
//        if (character.matches("[tf]")) return readBooleanToken(character);
        if (character == 102 || character == 116) return readBooleanToken(character);
        if (character == 110) return readNullToken(character);
//        if (character.matches("n")) return readNullToken(character);



        return null;
    }

    public Token readNullToken(int initialCharacter) {
        String resultBool = readLexeme(initialCharacter).toString();
        if (resultBool.equals(NullToken.NULL.getValue())) {
            return NullToken.NULL;
        }
        throw new MalformedJSONException("invalid syntax");
    }

    public Token readBooleanToken(int character) {
        String resultBool = readLexeme(character).toString();
        if (resultBool.equals(BooleanToken.TRUE.getValue())) {
            return BooleanToken.TRUE;
        } else if (resultBool.equals(BooleanToken.FALSE.getValue())) {
            return BooleanToken.FALSE;
        }
        throw new MalformedJSONException("Invalid syntax");
    }

    public StringBuilder readLexeme(int initialCharacter) {
        StringBuilder resultString = new StringBuilder();
        resultString.append((char) initialCharacter);
        while (!isEndedChars(reader.peekNext())) {
            if (reader.peekNext() == -1) break;
            resultString.append((char) reader.readNext());
        }
        return resultString;
    }

    public Token readNumberToken(int character) {
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
        int c;
        while (true) {
            c = reader.readNext();
            if (c == -1) throw new MalformedJSONException("Unterminated string");
            if (c == 34) break;
//            if (c == )
//            {
//                TODO: implements unicode hex encoded string
//            }
            else {
                resultString.append((char) c);
            }
        }
        return new StringToken(resultString.toString());
    }


}
