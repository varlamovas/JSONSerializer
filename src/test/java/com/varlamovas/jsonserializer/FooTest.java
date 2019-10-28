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

        Foo f = Foo.class.cast(new Object());

        List<Class> superClasses = FieldRetriever.getAllSuperClasses(Foo.class);
        try {
            Constructor<?> constructor = Foo.class.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Constructor<?>[] constructors = Foo.class.getConstructors();
        Constructor<?>[] declaredConstructors = Foo.class.getDeclaredConstructors();
        Constructor<?> constructor1 = declaredConstructors[0];
        constructor1.setAccessible(true);
        Foo instance = null;
        try {
            instance = (Foo) constructor1.newInstance(null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

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
