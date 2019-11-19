package com.varlamovas.jsonserializer.testobjects;

public class ClassWithTransientField {
    transient String transientStringField;

    void setFields() {
        transientStringField = "transientStringFieldValue";
    }

    public static ClassWithTransientField getInstance() {
        ClassWithTransientField instance = new ClassWithTransientField();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{}";
    }
}

