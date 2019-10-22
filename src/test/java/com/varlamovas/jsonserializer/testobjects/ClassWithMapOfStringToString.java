package com.varlamovas.jsonserializer.testobjects;

import java.util.HashMap;
import java.util.Map;

public class ClassWithMapOfStringToString implements BaseObject {
    public Map<String, String> mapOfStringToString = new HashMap<String, String>() {
        {
            put("firstKey", "firstValue");
            put("secondKey", "secondValue");
        }
    };
}
