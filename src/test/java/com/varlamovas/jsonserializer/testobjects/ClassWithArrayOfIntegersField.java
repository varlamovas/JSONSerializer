package com.varlamovas.jsonserializer.testobjects;

public class ClassWithArrayOfIntegersField implements BaseObject {
    int[] arrayOfIntegersField = {1, 2, 3, 4};

    public static String getJson() {
        return "{\"arrayOfIntegersField\":[1,2,3,4]}";
    }

    public static ClassWithArrayOfIntegersField getInstance() {
        return new ClassWithArrayOfIntegersField();
    }
}
