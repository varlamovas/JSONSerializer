package com.varlamovas.jsonserializer.testobjects;

public class ClassOnlyWithFinalFields implements BaseObject {
    final String stringField = "stringFieldValue";
    final int intField = 42;

    public static String getJson() {
        return "{\"stringField\":\"stringFieldValue\",\"intField\":42}";
    }

    public static BaseObject getInstance() {
        return new ClassOnlyWithFinalFields();
    }
}
