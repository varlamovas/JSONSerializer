package com.varlamovas.jsonserializer;

import java.util.NoSuchElementException;

public class IntBuffer {

    private static final int DEFAULT_CAPACITY = 4;
    private int[] intArray;
    private int pointer = 0;
    private int capacity;

    public IntBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The initial capacity must be a positive");
        }
        this.capacity = capacity;
        this.intArray = new int[this.capacity];
    }

    public IntBuffer() {
        this(IntBuffer.DEFAULT_CAPACITY);
    }

    public void add(int element) {
        if (pointer < capacity) {
            intArray[pointer] = element;
            pointer++;
        } else {
            throw new IllegalStateException("The element cannot be added due to capacity restrictions");
        }
    }

    public int poll() {
        if (pointer == 0) {
            throw new NoSuchElementException("The current buffer is empty");
        }
        return intArray[--pointer];
    }

    public Boolean isEmpty() {
        if (pointer == 0) return true;
        return false;
    }

    public Boolean isNotEmpty() {
        return !isEmpty();
    }
}
