package com.varlamovas.jsonserializer.testobjects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FooObject implements BaseObject {
    String stringField = "stringFieldValue";
    String nullInsteadString = null;
    byte byteField = 127;
    short shortField = 32767;
    char charField = 'c';
    int intField = 2147483647;
    long longField = 9223372036854775807L;
    float floatField = 3.4e+38f;
    double doubleField = 1.7e+308;
    boolean booleanField = true;
    Byte byteFieldBoxed = 127;
    Short shortFieldBoxed = 32767;
    Character charFieldBoxed = 'c';
    Integer intFieldBoxed = 2147483647;
    Long longFieldBoxed = 9223372036854775807L;
    Float floatFieldBoxed = 3.4e+38f;
    Double doubleFieldBoxed = 1.7e+308;
    Boolean booleanFieldBoxed = true;
    //    int[] array = {1, 2, 3, 4};
    List<Number> listOfNumbers = Arrays.asList(1, 2, 3, 4);
    Map<String, String> mapOfStringToString = new HashMap<String, String>() {{
        put("firstKey", "firstValue");
        put("secondKey", "secondValue");
    }};
}
