package com.varlamovas.jsonserializer.testobjects;

public class ClassWithSetter implements BaseObject {
    String settedString;

    public void setSettedString(String str) {
        settedString = str;
    }

    public static ClassWithSetter getInstanceOfClassWithSetters() {
        ClassWithSetter classWithSetter = new ClassWithSetter();
        classWithSetter.setSettedString("settedStringValue");
        return classWithSetter;
    }
}
