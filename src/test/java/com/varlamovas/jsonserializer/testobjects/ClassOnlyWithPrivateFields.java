package com.varlamovas.jsonserializer.testobjects;

public class ClassOnlyWithPrivateFields implements BaseObject {
    private String stringField = "stringFieldValue";
    private int intField = 42;

    public static String getJson() {
        return "{\"stringField\":\"stringFieldValue\",\"intField\":42}";
    }

    public static BaseObject getInstance() {
        return new ClassOnlyWithPrivateFields();
    }
}
