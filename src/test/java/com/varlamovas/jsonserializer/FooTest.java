package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.GenericSignatureFormatError;
import java.util.HashMap;
import java.util.Map;

public class FooTest {
    @Test
    void fooTest() {
        Gson gson = new Gson();
        Foo foo = new Foo();
        String json = gson.toJson(foo);
        System.out.println(json);
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
}
