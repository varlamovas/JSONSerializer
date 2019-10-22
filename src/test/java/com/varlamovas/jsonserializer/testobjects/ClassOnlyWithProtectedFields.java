package com.varlamovas.jsonserializer.testobjects;

public class ClassOnlyWithProtectedFields implements BaseObject {
    protected String stringField = "stringFieldValue";
    protected int intField = 42;

    public static String getJson() {
        return "{\"stringField\":\"stringFieldValue\",\"intField\":42}";
    }

    public static BaseObject getInstance() {
        return new ClassOnlyWithProtectedFields();
    }
}
