package com.varlamovas.jsonserializer.testobjects;

public class ClassPrimitives {

    byte byteField;
    short shortField;
    char charField;
    int intField;
    long longField;
    float floatField;
    double doubleField;
    boolean booleanField;

    private void setFields() {
        byteField = 127;
        shortField = 32767;
        charField = 'c';
        intField = 2147483647;
        longField = 9223372036854775807L;
        floatField = 3.4e+38f;
        doubleField = 1.7e+308;
        booleanField = true;
    }

    public static ClassPrimitives getInstance() {
        ClassPrimitives instance = new ClassPrimitives();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"byteField\":127,\"shortField\":32767,\"charField\":\"c\",\"intField\":2147483647,\"longField\":9223372036854775807,\"floatField\":3.4E38,\"doubleField\":1.7E308,\"booleanField\":true}";
    }
}
