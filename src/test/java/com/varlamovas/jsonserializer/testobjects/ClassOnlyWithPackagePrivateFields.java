package com.varlamovas.jsonserializer.testobjects;

public class ClassOnlyWithPackagePrivateFields implements BaseObject {
    String stringField = "stringFieldValue";
    int intField = 42;


    public static String getJson() {
        return "{\"stringField\":\"stringFieldValue\",\"intField\":42}";
    }

    public static BaseObject getInstance() {
        return new ClassOnlyWithPublicFields();
    }
}
