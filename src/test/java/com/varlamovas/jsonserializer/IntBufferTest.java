package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IntBufferTest {

    private static IntBuffer intBufferDefaultSize;

    @BeforeEach
    void setUpTest() {
        intBufferDefaultSize = new IntBuffer();
    }

    @Test
    void add() {
        intBufferDefaultSize.add(1);
        intBufferDefaultSize.add(2);
        intBufferDefaultSize.add(3);
        intBufferDefaultSize.add(4);
        assertThrows(IllegalStateException.class, () -> intBufferDefaultSize.add(4));
    }

    @Test
    void poll() {
        assertThrows(NoSuchElementException.class, () -> intBufferDefaultSize.poll());

        intBufferDefaultSize.add(1);
        assertEquals(1, intBufferDefaultSize.poll());

        intBufferDefaultSize.add(1);
        intBufferDefaultSize.add(2);
        intBufferDefaultSize.add(3);
        intBufferDefaultSize.add(4);
        assertEquals(4, intBufferDefaultSize.poll());
        assertEquals(3, intBufferDefaultSize.poll());
        assertEquals(2, intBufferDefaultSize.poll());
        assertEquals(1, intBufferDefaultSize.poll());

        assertThrows(NoSuchElementException.class, () -> intBufferDefaultSize.poll());
    }

    @Test
    void isEmpty() {
        assertTrue(intBufferDefaultSize.isEmpty());

        intBufferDefaultSize.add(1);
        assertFalse(intBufferDefaultSize.isEmpty());

        intBufferDefaultSize.poll();
        assertTrue(intBufferDefaultSize.isEmpty());
    }

    @Test
    void isNotEmpty() {
        assertFalse(intBufferDefaultSize.isNotEmpty());

        intBufferDefaultSize.add(1);
        assertTrue(intBufferDefaultSize.isNotEmpty());

        intBufferDefaultSize.poll();
        assertFalse(intBufferDefaultSize.isNotEmpty());
    }
}