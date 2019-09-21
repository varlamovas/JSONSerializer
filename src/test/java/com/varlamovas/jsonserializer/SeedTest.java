package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeedTest {


    @Test
    void newInstanceTest() {
        ClassSimple classSimple = new ClassSimple();
        Seed<ClassSimple> seed = new Seed(classSimple.getClass());
        ClassSimple classSimpleNew = seed.newInstance();
    }
}