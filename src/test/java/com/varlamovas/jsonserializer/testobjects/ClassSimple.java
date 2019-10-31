package com.varlamovas.jsonserializer.testobjects;

public class ClassSimple {

    public String stringField;
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    @Override
    public String toString() {
        return "ClassSimple_" + "stringField_" + stringField;
    }
}
