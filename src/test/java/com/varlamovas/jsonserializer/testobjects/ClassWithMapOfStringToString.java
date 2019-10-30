package com.varlamovas.jsonserializer.testobjects;

import java.util.HashMap;
import java.util.Map;

public class ClassWithMapOfStringToString implements BaseObject {
    public Map<String, String> mapOfStringToString;

    private void setFields() {
        mapOfStringToString = new HashMap<>();
        mapOfStringToString.put("firstKey", "firstValue");
        mapOfStringToString.put("secondKey", "secondValue");

    }

    public static ClassWithMapOfStringToString getInstance() {
        ClassWithMapOfStringToString instance = new ClassWithMapOfStringToString();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"mapOfStringToString\":{\"firstKey\":\"firstValue\",\"secondKey\":\"secondValue\"}}";
    }
}
