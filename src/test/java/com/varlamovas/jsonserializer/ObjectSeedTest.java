package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.seed.ObjectSeed;
import org.junit.jupiter.api.Test;

class ObjectSeedTest {


    @Test
    void newInstanceTest() {
        ClassSimple classSimple = new ClassSimple();
        ObjectSeed<ClassSimple> objectSeed = new ObjectSeed(classSimple.getClass());
        ClassSimple classSimpleNew = objectSeed.newInstance();
    }
}