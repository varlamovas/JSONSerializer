package com.varlamovas.jsonserializer.testobjects;

public class ClassWithArrayOfIntegersField implements BaseObject {
    int[] arrayOfIntegersField;


    void setFields() {
        arrayOfIntegersField = new int[]{1, 2, 3, 4};
    }


    public static String getJson() {
        return "{\"arrayOfIntegersField\":[1,2,3,4]}";
    }

    public static ClassWithArrayOfIntegersField getInstance() {

        ClassWithArrayOfIntegersField instance = new ClassWithArrayOfIntegersField();
        instance.setFields();
        return instance;
    }
}
