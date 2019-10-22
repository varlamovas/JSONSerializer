package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayList;
import java.util.List;

public class ClassWithArrayListOfStringField implements BaseObject {
    public List<String> listOfStrings;

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

}
