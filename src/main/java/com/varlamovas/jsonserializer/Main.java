package com.varlamovas.jsonserializer;

import java.util.ArrayList;
import java.util.List;


//Todo: Only for test, finally it will be remove
public class Main {
    public static void main(String[] args) {
        while (true) {
            JsonSerializer jsonSerializer = new JsonSerializer();
            ClassWithLargeList testClass = ClassWithLargeList.getInstanceofClassWithLargeList(1_000_000);
            jsonSerializer.deserialize(jsonSerializer.serialize(testClass), ClassWithLargeList.class);
        }
    }
}

class ClassWithLargeList {
    List<CollectionEntry> listOfCollectionEntry = new ArrayList<>();

    private void setLargeList(int capacity) {
        for (int i = 0; i < capacity; ++i) {
            listOfCollectionEntry.add(CollectionEntry.getInstance("name" + i, "value" + i));
        }
    }

    static ClassWithLargeList getInstanceofClassWithLargeList(int capacity) {
        ClassWithLargeList instance = new ClassWithLargeList();
        instance.setLargeList(capacity);
        return instance;
    }

    static ClassWithLargeList getInstanceofClassWithLargeList() {
        int defaultCapacity = 1_000_000;
        return getInstanceofClassWithLargeList(defaultCapacity);
    }

}

class CollectionEntry {
    private String name;
    private String value;

    void setFields(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static CollectionEntry getInstance(String name, String value) {
        CollectionEntry instance = new CollectionEntry();
        instance.setFields(name, value);
        return instance;
    }
}
