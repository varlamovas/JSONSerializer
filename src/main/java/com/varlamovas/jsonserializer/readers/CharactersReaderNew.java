package com.varlamovas.jsonserializer.readers;

import com.varlamovas.jsonserializer.MalformedJSONException;
import com.varlamovas.jsonserializer.StringBuffer;

import java.io.IOException;
import java.io.StringReader;

public class CharactersReaderNew implements CharacterReader {
    private static final int BUFFER_SIZE = 4;

    private StringReader reader;
    private StringBuffer stringBuffer;

    public CharactersReaderNew(StringReader reader) {
        this.reader = reader;
        stringBuffer = new StringBuffer(CharactersReaderNew.BUFFER_SIZE);
    }

    @Override
    public String readNext() {
        String next;
        if (stringBuffer.isNotEmpty()) {
            next = stringBuffer.poll();
        } else {
            next = popFromReader();
        }
        return next;
    }

    @Override
    public String peekNext() {
        String peeked = readNext();
        stringBuffer.add(peeked);
        return peeked;
    }

    private String popFromReader() {
        int ch;
        try {
            ch = reader.read();
        } catch (IOException e) {
            //TODO: think about it exception, maybe can define custom exeption class.
            throw new MalformedJSONException("IO exception can be catched", e.getCause());
        }
        if (ch == -1) {
            return null;
        }
        return String.valueOf((char) ch);

    }
}
