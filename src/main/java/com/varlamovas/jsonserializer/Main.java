package com.varlamovas.jsonserializer;

import java.util.ArrayList;
import java.util.List;


//Todo: Only for test, finally it will be remove
public class Main {
    public static void main(String[] args) {

//        char c11111 = 'n';
//        System.out.print("n");
//        System.out.println((int) c11111);
//
//        char c11112 = 'f';
//        System.out.print("f");
//        System.out.println((int) c11112);
//
//        char c11113 = 't';
//        System.out.print("t");
//        System.out.println((int) c11113);
//
//        char c1111 = '-';
//        System.out.print("-");
//        System.out.println((int) c1111);
//
//        char c111 = '"';
//        System.out.print("\"");
//        System.out.println((int) c111);
//
//
//        char c11 = ' ';
//        System.out.print("space ");
//        System.out.println((int) c11);
//
//
//        char c12 = '\n';
//        System.out.print("n ");
//        System.out.println((int) c12);
//
//
//        char c13 = '\t';
//        System.out.print("t ");
//        System.out.println((int) c13);
//
//        char c14 = '\r';
//        System.out.print("r ");
//        System.out.println((int) c14);
//
//        char c1 = '{';
//        System.out.print("{ ");
//        System.out.println((int) c1);
//
//        char c2 = '}';
//        System.out.print("} ");
//        System.out.println((int) c2);
//
//        char c3 = '[';
//        System.out.print("[ ");
//        System.out.println((int) c3);
//
//        char c4 = ']';
//        System.out.print("] ");
//        System.out.println((int) c4);
//
//        char c5 = ':';
//        System.out.print(": ");
//        System.out.println((int) c5);
//
//        char c6 = ',';
//        System.out.print(", ");
//        System.out.println((int) c6);


        while (true) {
            JsonSerializer jsonSerializer = new JsonSerializer();
            ClassWithLargeList testClass = ClassWithLargeList.getInstanceofClassWithLargeList(500_000);
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
