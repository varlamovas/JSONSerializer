package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayList;

public class ClassWithArrayListOfStringField implements BaseObject {
    public ArrayList<String> listOfStrings;

    private void setListOfStrings() {
        listOfStrings = new ArrayList<>();
        listOfStrings.add("list");
        listOfStrings.add("Of");
        listOfStrings.add("Strings");
    }

    public static ClassWithArrayListOfStringField getInstance() {
        ClassWithArrayListOfStringField inst = new ClassWithArrayListOfStringField();
        inst.setListOfStrings();
        return inst;
    }

    public static String getJson() {
        return "{\"listOfStrings\":[\"list\",\"Of\",\"Strings\"]}";
    }
}
