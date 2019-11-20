package com.varlamovas.jsonserializer.readers;

import com.varlamovas.jsonserializer.IntBuffer;
import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;

import java.io.IOException;
import java.io.StringReader;

public class ReaderChars {
    private static final int BUFFER_SIZE = 4;

    private StringReader reader;
    private IntBuffer intBuffer;
    private boolean eof = false;

    public ReaderChars(StringReader reader) {
        this.reader = reader;
        intBuffer = new IntBuffer(ReaderChars.BUFFER_SIZE);
    }

    public int readNext() {
//        if (eof) {
//            return -1;
//        }
        int next;
        if (intBuffer.isNotEmpty()) {
            next = intBuffer.poll();
        } else {
            next = popFromReader();
        }
        return next;
    }

    public int peekNext() {
        int peeked = readNext();
        intBuffer.add(peeked);
//        if (eof) {
//            return -1;
//        }
        return peeked;
    }

    private int popFromReader() {
        int ch;
        try {
            ch = reader.read();
        } catch (IOException e) {
            //TODO: think about it exception, maybe can define custom exeption class.
            throw new MalformedJSONException("IO exception can be catched", e.getCause());
        }
        if (ch == -1) {
//            eof = true;
            return -1;
        }
        return ch;

    }
}
