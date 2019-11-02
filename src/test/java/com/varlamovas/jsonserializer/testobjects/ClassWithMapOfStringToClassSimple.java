package com.varlamovas.jsonserializer.testobjects;

import java.util.HashMap;
import java.util.Map;

public class ClassWithMapOfStringToClassSimple {
    public Map<String, ClassSimple> mapStringToClassSimple;

    public static ClassWithMapOfStringToClassSimple getInstance(){
        ClassWithMapOfStringToClassSimple instance = new ClassWithMapOfStringToClassSimple();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"mapStringToClassSimple\":{\"classSimpleField1\":{\"stringField\":\"simple1\"},\"classSimpleField2\":{\"stringField\":\"simple2\"}}}";
    }

    private void setFields() {
        mapStringToClassSimple = new HashMap<>();
        ClassSimple simple1 = new ClassSimple();
        simple1.setStringField("simple1");
        mapStringToClassSimple.put("classSimpleField1", simple1);

        ClassSimple simple2 = new ClassSimple();
        simple2.setStringField("simple2");
        mapStringToClassSimple.put("classSimpleField2", simple2);
    }
}
