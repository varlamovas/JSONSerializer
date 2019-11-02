package com.varlamovas.jsonserializer.testobjects;

public class ClassWithArrayOfClassSimple {

    ClassSimple[] fieldClassSimple;

    public static ClassWithArrayOfClassSimple getInstance() {
        ClassWithArrayOfClassSimple instance = new ClassWithArrayOfClassSimple();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"fieldClassSimple\":[{\"stringField\":\"simple1\"},{\"stringField\":\"simple2\"}]}";
    }

    private void setFields() {
        fieldClassSimple = new ClassSimple[2];
        ClassSimple simple1 = ClassSimple.getInstance("simple1");
        ClassSimple simple2 = ClassSimple.getInstance("simple2");
        fieldClassSimple[0] = simple1;
        fieldClassSimple[1] = simple2;
    }
}
