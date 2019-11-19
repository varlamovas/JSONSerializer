package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassWithMapOfStringToListOfString {
    Map<String, List<String>> mapOfStringToListOfString;

    void setFields() {
        List<String> listOfString1 = new ArrayList<>();
        listOfString1.add("stringOne");
        listOfString1.add("stringTwo");
        List<String> listOfString2 = new ArrayList<>();
        listOfString2.add("stringOne");
        listOfString2.add("stringTwo");
        mapOfStringToListOfString = new HashMap<>();
        mapOfStringToListOfString.put("firstList", listOfString1);
        mapOfStringToListOfString.put("secondList", listOfString2);
    }

    public static ClassWithMapOfStringToListOfString getInstance() {
        ClassWithMapOfStringToListOfString instance = new ClassWithMapOfStringToListOfString();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"mapOfStringToListOfString\":{\"secondList\":[\"stringOne\",\"stringTwo\"],\"firstList\":[\"stringOne\",\"stringTwo\"]}}";
    }
}
