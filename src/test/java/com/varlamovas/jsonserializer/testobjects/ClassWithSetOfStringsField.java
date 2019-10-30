package com.varlamovas.jsonserializer.testobjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassWithSetOfStringsField implements BaseObject {
    Set<String> setOfStrings = new HashSet<>(Arrays.asList("set", "Of", "Strings"));

    public static ClassWithSetOfStringsField getInstance() {
        ClassWithSetOfStringsField instance = new ClassWithSetOfStringsField();
        return instance;
    }

    public static String getJson() {
        return "{\"setOfStrings\":[\"set\",\"Of\",\"Strings\"]}";
    }
}
