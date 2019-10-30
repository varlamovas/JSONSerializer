package com.varlamovas.jsonserializer.testobjects;

public class ClassWithSetter implements BaseObject {
    String settedString;

    private void setSettedString(String str) {
        settedString = str;
    }

    public static ClassWithSetter getInstance() {
        ClassWithSetter classWithSetter = new ClassWithSetter();
        classWithSetter.setSettedString("settedStringValue");
        return classWithSetter;
    }

    public static String getJson() {
        return "{\"settedString\":\"settedStringValue\"}";
    }
}
