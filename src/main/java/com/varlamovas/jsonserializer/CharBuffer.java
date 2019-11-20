package com.varlamovas.jsonserializer;

import java.util.NoSuchElementException;

public class CharBuffer {

    private static final int DEFAULT_CAPACITY = 4;
    private char[] charArray;
    private int pointer = 0;
    private int capacity;

    public CharBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The initial capacity must be a positive");
        }
        this.capacity = capacity;
        this.charArray = new char[this.capacity];
    }

    public CharBuffer() {
        this(CharBuffer.DEFAULT_CAPACITY);
    }

    public void add(char element) {
        if (pointer < capacity) {
            charArray[pointer] = element;
            pointer++;
        } else {
            throw new IllegalStateException("The element cannot be added due to capacity restrictions");
        }
    }

    public char poll() {
        if (pointer == 0) {
            throw new NoSuchElementException("The current buffer is empty");
        }
        return charArray[--pointer];
    }

    public Boolean isEmpty() {
        return pointer == 0;
    }

    public Boolean isNotEmpty() {
        return !isEmpty();
    }
}
