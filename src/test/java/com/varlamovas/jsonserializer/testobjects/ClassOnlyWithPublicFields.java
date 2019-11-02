package com.varlamovas.jsonserializer.testobjects;

public class ClassOnlyWithPublicFields implements BaseObject {
    public String stringField = "stringFieldValue";
    public int intField = 42;


    public static String getJson() {
        return "{\"stringField\":\"stringFieldValue\",\"intField\":42}";
    }

    public static BaseObject getInstance() {
        return new ClassOnlyWithPublicFields();
    }
}
