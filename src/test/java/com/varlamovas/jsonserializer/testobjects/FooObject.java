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
    int[] arrayInt = {1, 2, 3, 4};
    String[] arrayString = {"one", "two", "three"};
    List<Number> listOfNumbers = Arrays.asList(1, 2, 3, 4);
    Map<String, String> mapOfStringToString = new HashMap<String, String>() {{
        put("firstKey", "firstValue");
        put("secondKey", "secondValue");
    }};

    public static String getJson() {
        return "{\"stringField\":\"stringFieldValue\",\"byteField\":127,\"shortField\":32767,\"charField\":\"c\",\"intField\":2147483647,\"longField\":9223372036854775807,\"floatField\":3.4E38,\"doubleField\":1.7E308,\"booleanField\":true,\"byteFieldBoxed\":127,\"shortFieldBoxed\":32767,\"charFieldBoxed\":\"c\",\"intFieldBoxed\":2147483647,\"longFieldBoxed\":9223372036854775807,\"floatFieldBoxed\":3.4E38,\"doubleFieldBoxed\":1.7E308,\"booleanFieldBoxed\":true,\"arrayInt\":[1,2,3,4],\"arrayString\":[\"one\",\"two\",\"three\"],\"listOfNumbers\":[1,2,3,4],\"mapOfStringToString\":{\"firstKey\":\"firstValue\",\"secondKey\":\"secondValue\"}}";
    }

    public static FooObject getInstance() {
        FooObject instance = new FooObject();
        return instance;
    }
}
