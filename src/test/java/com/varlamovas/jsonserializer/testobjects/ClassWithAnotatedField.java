package com.varlamovas.jsonserializer.testobjects;

import com.varlamovas.jsonserializer.annotations.CustomName;

public class ClassWithAnotatedField {

    @CustomName(name = "jui")
    public String  annotatedField;

    void setFields() {
        annotatedField = "annotatedField";
    }

    public static ClassWithAnotatedField getInstance() {
        ClassWithAnotatedField instance = new ClassWithAnotatedField();
        instance.setFields();
        return instance;
    }
}
