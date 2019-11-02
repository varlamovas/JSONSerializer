package com.varlamovas.jsonserializer.testobjects;

import java.util.Objects;

public class ClassSimple {

    public String stringField;
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public static ClassSimple getInstance(String stringField) {
        ClassSimple instance = new ClassSimple();
        instance.setStringField(stringField);
        return instance;
    }

    @Override
    public String toString() {
        return "ClassSimple_" + "stringField_" + stringField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassSimple that = (ClassSimple) o;
        return Objects.equals(stringField, that.stringField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringField);
    }
}
