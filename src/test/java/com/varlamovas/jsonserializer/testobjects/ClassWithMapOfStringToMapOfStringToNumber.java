package com.varlamovas.jsonserializer.testobjects;

import java.util.HashMap;
import java.util.Map;

public class ClassWithMapOfStringToMapOfStringToNumber implements BaseObject {
    Map<String, Map<String, Number>> mapOfStringToMapOfStringToNumber = new HashMap<String, Map<String, Number>>() {
        {
            put("firstKey", new HashMap<String, Number>() {{
                put("firstFirstKey", 1);
                put("firstSecondKey", 2);
            }});
            put("secondKey", new HashMap<String, Number>() {{
                put("secondFirstKey", 1);
                put("secondSecondKey", 2);
            }});
        }
    };
}
