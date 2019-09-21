package com.varlamovas.jsonserializer;

import java.lang.reflect.Field;

public class Foo{
    public static void main(String[] args) {
        Bar sergey = new Bar(34, "Sergey");
        Field[] fields = sergey.getClass().getFields();
//        System.out.println(fields);
    }
}

class Entity {
    static int staticField = 32;
    static int staticFieldInh = 42;
    volatile int id;
    String name;
    transient long random;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Bar extends Entity {

    static int staticFieldInh = 52;
    String surname = "surname";

    public Bar(int id, String name) {
        super(id, name);
    }
}
