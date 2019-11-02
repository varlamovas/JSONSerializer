package com.varlamovas.jsonserializer.testobjects;

import java.util.Arrays;
import java.util.List;

public class ClassWithListOfStringField implements BaseObject {
    public List<String> listOfStrings;

    private void setListOfStrings() {
        listOfStrings = Arrays.asList("list", "Of", "Strings");
    }

    public static ClassWithListOfStringField getInstance() {
        ClassWithListOfStringField inst = new ClassWithListOfStringField();
        inst.setListOfStrings();
        return inst;
    }

    public static String getJson() {
        return "{\"listOfStrings\":[\"list\",\"Of\",\"Strings\"]}";
    }
}
