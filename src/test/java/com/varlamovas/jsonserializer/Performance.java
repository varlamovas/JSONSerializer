package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Performance {

    static JsonSerializer jsonSerializer;
    static Gson gsonSerializer;
    static ClassWithLargeList testClass;

    @BeforeEach
    void setUpTest() {
        jsonSerializer = new JsonSerializer();
        gsonSerializer = new Gson();
        testClass = ClassWithLargeList.getInstanceofClassWithLargeList(1_000_000);
    }

    @Test
    void testPerformanceGson() {
        gsonSerializer.toJson(testClass);
    }

    @Test
    void testPerformanceJsonserializer() {
        jsonSerializer.serialize(testClass);
    }

    @Test
    void testPerformanceGsonDeserialize() {
        gsonSerializer.fromJson(gsonSerializer.toJson(testClass), ClassWithLargeList.class);
    }

    @Test
    void testPerformanceJsonserializerDeserialize() {
        jsonSerializer.deserialize(jsonSerializer.serialize(testClass), ClassWithLargeList.class);
    }

//    @Test
//    void testResult() {
//        String gsonString = gsonSerializer.toJson(testClass);
//        String jsonserializerstring = jsonSerializer.serialize(testClass);
//        assertEquals( gsonString, jsonserializerstring);
//    }
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