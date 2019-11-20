package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FooTest {
    @Test
    void fooTest() {

    }

    @Test
    void fooTest2() {

    }
}

class Foo {
    Map<String, String> mapField = new HashMap<String, String>() {{
        put("notNullField", "notNullField");
        put("nullField", null);
    }};
    String stringField = "stringField";
    String foo;
    Foo(String foo) {
        this.foo = foo;
    }
}
