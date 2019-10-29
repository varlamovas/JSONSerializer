package com.varlamovas.jsonserializer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("some");
        list.add("string");
        Primer<String> primer = new Primer<>(list);
        System.out.println(primer);
    }
}

class Primer<T> {

    T foo;

    List<T> list = new ArrayList<>();

    Primer(List<T> list) {
        System.out.println();
        this.list = list;
    }
}
