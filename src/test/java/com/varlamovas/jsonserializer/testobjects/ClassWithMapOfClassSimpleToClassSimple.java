package com.varlamovas.jsonserializer.testobjects;

import java.util.HashMap;
import java.util.Map;

public class ClassWithMapOfClassSimpleToClassSimple {
    public Map<ClassSimple, ClassSimple> mapStringToClassSimple;

    public static ClassWithMapOfClassSimpleToClassSimple getInstance(){
        ClassWithMapOfClassSimpleToClassSimple instance = new ClassWithMapOfClassSimpleToClassSimple();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"mapStringToClassSimple\":{\"ClassSimple_stringField_property1\":{\"stringField\":\"value1\"},\"ClassSimple_stringField_property2\":{\"stringField\":\"value2\"}}}";
    }

    private void setFields() {
        mapStringToClassSimple = new HashMap<>();
        ClassSimple property1 = new ClassSimple();
        property1.setStringField("property1");
        ClassSimple value1 = new ClassSimple();
        value1.setStringField("value1");

        ClassSimple property2 = new ClassSimple();
        property2.setStringField("property2");
        ClassSimple value2 = new ClassSimple();
        value2.setStringField("value2");
        mapStringToClassSimple.put(property1, value1);
        mapStringToClassSimple.put(property2, value2);
    }

}