package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.seed.ObjectSeed;
import com.varlamovas.jsonserializer.testobjects.ClassSimple;
import org.junit.jupiter.api.Test;

class ObjectSeedTest {


    @Test
    void newInstanceTest() {
        ClassSimple classSimple = new ClassSimple();
        ObjectSeed objectSeed = new ObjectSeed(classSimple.getClass());
        ClassSimple classSimpleNew = (ClassSimple) objectSeed.newInstance();
    }
}