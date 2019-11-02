package com.varlamovas.jsonserializer;

import java.util.NoSuchElementException;

public class StringBuffer {

    private static final int DEFAULT_CAPACITY = 4;
    private String[] stringArray;
    private int pointer = 0;
    private int capacity;

    public StringBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The initial capacity must be a positive");
        }
        this.capacity = capacity;
        this.stringArray = new String[this.capacity];
    }

    public StringBuffer() {
        this(StringBuffer.DEFAULT_CAPACITY);
    }

    public void add(String element) {
        if (pointer < capacity) {
            stringArray[pointer] = element;
            pointer++;
        } else {
            throw new IllegalStateException("The element cannot be added due to capacity restrictions");
        }
    }

    public String poll() {
        if (pointer == 0) {
            throw new NoSuchElementException("The current buffer is empty");
        }
        return stringArray[--pointer];
    }

    public Boolean isEmpty() {
        if (pointer == 0) return true;
        return false;
    }

    public Boolean isNotEmpty() {
        return !isEmpty();
    }
}
