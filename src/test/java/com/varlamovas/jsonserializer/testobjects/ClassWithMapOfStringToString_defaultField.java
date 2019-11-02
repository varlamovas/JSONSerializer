package com.varlamovas.jsonserializer.testobjects;

import java.util.HashMap;
import java.util.Map;

public class ClassWithMapOfStringToString_defaultField implements BaseObject {
    public Map<String, String> mapOfStringToString = new HashMap<String, String>() {
        {
            put("firstKey", "firstValue");
            put("secondKey", "secondValue");
        }
    };
    public static ClassWithMapOfStringToString_defaultField getInstance() {
        ClassWithMapOfStringToString_defaultField instance = new ClassWithMapOfStringToString_defaultField();
        return instance;
    }

    public static String getJson() {
        return "{\"mapOfStringToString\":{\"firstKey\":\"firstValue\",\"secondKey\":\"secondValue\"}}";
    }
}
