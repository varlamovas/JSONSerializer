package com.varlamovas.jsonserializer.testobjects;

import java.util.HashMap;
import java.util.Map;

public class ClassWithMapOfNumberToNumber implements BaseObject {
    Map<Number, Number> mapOfStringToString = new HashMap<Number, Number>() {
        {
            put(1, 1);
            put(2, 2);
        }
    };
}
