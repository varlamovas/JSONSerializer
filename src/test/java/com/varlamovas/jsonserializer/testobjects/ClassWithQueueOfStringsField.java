package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ClassWithQueueOfStringsField implements BaseObject {
    Queue<String> queueOfStrings = new ArrayDeque<>(Arrays.asList("queue", "Of", "Strings"));

    public static String getJson() {
        return "{\"queueOfStrings\":[\"queue\",\"Of\",\"Strings\"]}";
    }

    public static ClassWithQueueOfStringsField getInstance() {
        ClassWithQueueOfStringsField instance = new ClassWithQueueOfStringsField();
        return instance;
    }
}
