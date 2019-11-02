package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StringBufferTest {

    private static StringBuffer stringBufferDefaultSize;

    @BeforeEach
    void setUpTest() {
        stringBufferDefaultSize = new StringBuffer();
    }

    @Test
    void add() {
        stringBufferDefaultSize.add("a");
        stringBufferDefaultSize.add("b");
        stringBufferDefaultSize.add("c");
        stringBufferDefaultSize.add("d");
        assertThrows(IllegalStateException.class, () -> stringBufferDefaultSize.add("e"));
    }

    @Test
    void poll() {
        assertThrows(NoSuchElementException.class, () -> stringBufferDefaultSize.poll());

        stringBufferDefaultSize.add("a");
        assertEquals("a", stringBufferDefaultSize.poll());

        stringBufferDefaultSize.add("a");
        stringBufferDefaultSize.add("b");
        stringBufferDefaultSize.add("c");
        stringBufferDefaultSize.add("d");
        assertEquals("d", stringBufferDefaultSize.poll());
        assertEquals("c", stringBufferDefaultSize.poll());
        assertEquals("b", stringBufferDefaultSize.poll());
        assertEquals("a", stringBufferDefaultSize.poll());

        assertThrows(NoSuchElementException.class, () -> stringBufferDefaultSize.poll());
    }

    @Test
    void isEmpty() {
        assertTrue(stringBufferDefaultSize.isEmpty());

        stringBufferDefaultSize.add("a");
        assertFalse(stringBufferDefaultSize.isEmpty());

        stringBufferDefaultSize.poll();
        assertTrue(stringBufferDefaultSize.isEmpty());
    }

    @Test
    void isNotEmpty() {
        assertFalse(stringBufferDefaultSize.isNotEmpty());

        stringBufferDefaultSize.add("a");
        assertTrue(stringBufferDefaultSize.isNotEmpty());

        stringBufferDefaultSize.poll();
        assertFalse(stringBufferDefaultSize.isNotEmpty());
    }
}