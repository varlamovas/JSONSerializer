package com.varlamovas.jsonserializer.testobjects;

public class CustomClass {

    public String stringField;
    public Byte byteFieldBoxed;
    public Short shortFieldBoxed;
    public Character charFieldBoxed;
    public Integer intFieldBoxed;
    public Long longFieldBoxed;
    public Float floatFieldBoxed;
    public Double doubleFieldBoxed;
    public Boolean booleanFieldBoxed;

    public void setAllFIelds() {
        stringField = "stringFieldValue";
        byteFieldBoxed = 127;
        shortFieldBoxed = 32767;
        charFieldBoxed = 'c';
        intFieldBoxed = 2147483647;
        longFieldBoxed = 9223372036854775807L;
        floatFieldBoxed = 3.4e+38f;
        doubleFieldBoxed = 1.7e+308;
        booleanFieldBoxed = true;
    }

    public static CustomClass getInstance() {
        CustomClass instance = new CustomClass();
        instance.setAllFIelds();
        return instance;
    }
}
