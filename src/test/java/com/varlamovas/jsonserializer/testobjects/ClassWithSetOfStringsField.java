package com.varlamovas.jsonserializer.testobjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassWithSetOfStringsField implements BaseObject {
    Set<String> setOfStrings = new HashSet<>(Arrays.asList("set", "Of", "Strings"));
}
