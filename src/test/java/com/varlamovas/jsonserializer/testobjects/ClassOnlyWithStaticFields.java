package com.varlamovas.jsonserializer.testobjects;

public class ClassOnlyWithStaticFields implements BaseObject {
    static String stringField = "stringFieldValue";
    static int intField = 42;

    public static String getJson() {
        return "{}";
    }

    public static BaseObject getInstance() {
        return new ClassOnlyWithStaticFields();
    }
}
