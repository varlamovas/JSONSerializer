package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CharBufferTest {

    private static CharBuffer charBufferDefaultSize;

    @BeforeEach
    void setUpTest() {
        charBufferDefaultSize = new CharBuffer();
    }

    @Test
    void add() {
        charBufferDefaultSize.add((char) 4);
        charBufferDefaultSize.add((char) 5);
        charBufferDefaultSize.add((char) 6);
        charBufferDefaultSize.add((char) 7);
        assertThrows(IllegalStateException.class, () -> charBufferDefaultSize.add((char) 8));
    }

    @Test
    void poll() {
        assertThrows(NoSuchElementException.class, () -> charBufferDefaultSize.poll());

        charBufferDefaultSize.add((char) 1);
        assertEquals((char) 1, charBufferDefaultSize.poll());

        charBufferDefaultSize.add((char) 1);
        charBufferDefaultSize.add((char) 2);
        charBufferDefaultSize.add((char) 3);
        charBufferDefaultSize.add((char) 4);
        assertEquals((char) 4, charBufferDefaultSize.poll());
        assertEquals((char) 3, charBufferDefaultSize.poll());
        assertEquals((char) 2, charBufferDefaultSize.poll());
        assertEquals((char) 1, charBufferDefaultSize.poll());

        assertThrows(NoSuchElementException.class, () -> charBufferDefaultSize.poll());
    }

    @Test
    void isEmpty() {
        assertTrue(charBufferDefaultSize.isEmpty());

        charBufferDefaultSize.add((char) 4);
        assertFalse(charBufferDefaultSize.isEmpty());

        charBufferDefaultSize.poll();
        assertTrue(charBufferDefaultSize.isEmpty());
    }

    @Test
    void isNotEmpty() {
        assertFalse(charBufferDefaultSize.isNotEmpty());

        charBufferDefaultSize.add((char) 1);
        assertTrue(charBufferDefaultSize.isNotEmpty());

        charBufferDefaultSize.poll();
        assertFalse(charBufferDefaultSize.isNotEmpty());
    }
}