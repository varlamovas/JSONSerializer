package com.varlamovas.jsonserializer.testobjects;

import java.util.Arrays;
import java.util.List;

public class ClassWithListOfListOfStringsField implements BaseObject {
    List<List<String>> listOfListOfStrings = Arrays.asList(
            Arrays.asList("first", "list", "Of", "Strings"),
            Arrays.asList("second", "list", "Of", "Strings")
    );
}
