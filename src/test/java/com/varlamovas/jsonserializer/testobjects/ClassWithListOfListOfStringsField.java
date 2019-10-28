package com.varlamovas.jsonserializer.testobjects;

import java.util.Arrays;
import java.util.List;

public class ClassWithListOfListOfStringsField implements BaseObject {
    public List<List<String>> listOfListOfStrings;

    private void setListOfListOfStrings() {
        listOfListOfStrings = Arrays.asList(
                Arrays.asList("first", "list", "Of", "Strings"),
                Arrays.asList("second", "list", "Of", "Strings")
        );
    }

    public static ClassWithListOfListOfStringsField getInstance() {
        ClassWithListOfListOfStringsField instance = new ClassWithListOfListOfStringsField();
        instance.setListOfListOfStrings();
        return instance;
    }

    public static String toJson() {
        return "{\"listOfListOfStrings\":[[\"first\",\"list\",\"Of\",\"Strings\"],[\"second\",\"list\",\"Of\",\"Strings\"]]}";
    }
}
