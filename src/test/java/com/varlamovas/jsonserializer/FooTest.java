package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
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
