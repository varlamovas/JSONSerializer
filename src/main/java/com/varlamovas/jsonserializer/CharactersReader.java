package com.varlamovas.jsonserializer;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

public class CharactersReader {

    private static final int BUFFER_SIZE = 4;

    private PushbackReader reader;
    private char[] tokenBuffer = new char[CharactersReader.BUFFER_SIZE];
    private Boolean eof = false;
    private Character nextChar = null;

    public CharactersReader(Reader reader) {
        this.reader = new PushbackReader(reader);
    }

    private void advance() {
        if (!eof) {
            int c = 0;
            try {
                c = reader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (c == -1) {
                eof = true;
            } else {
                nextChar = (char) c;
            }
        }
    }

    public String pickNext() {
        advance();
        if (eof) return null;
        Character ch = nextChar;
        nextChar = null;
        try {
            reader.unread((int) ch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(ch);

    }

    private String popNext() {
        if (nextChar == null) {
            advance();
        }
        if (eof) {
            return null;
        } else {
            return String.valueOf(nextChar);
        }
    }

    public String readNext() {
        String c = popNext();
        nextChar = null;
        return c;
    }

    public String readNextCharacters(int length) {
        int c = 0;
        if (eof) {
            return null;
        }
        if (length > BUFFER_SIZE) {
            throw new MalformedJSONException("Length greater then buffer size");
        }
        try {
            c = reader.read(tokenBuffer, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (c != length) {
            throw new MalformedJSONException("Premature end of data");
        }
        return new String(tokenBuffer, 0, length);
    }

}
