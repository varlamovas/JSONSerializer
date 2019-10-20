package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isWhitespace() {
        assertTrue(StringUtils.isWhitespace("   "));
        assertTrue(StringUtils.isWhitespace("\t\n "));
        assertFalse(StringUtils.isWhitespace("dsfg"));
        assertFalse(StringUtils.isWhitespace(" kjas "));
    }

    @Test
    void isNumber() {
        assertTrue(StringUtils.isNumber("1"));
        assertTrue(StringUtils.isNumber("0"));
        assertTrue(StringUtils.isNumber("10"));
        assertFalse(StringUtils.isNumber(" 12"));
        assertFalse(StringUtils.isNumber("-"));
        assertFalse(StringUtils.isNumber(" "));
    }
}