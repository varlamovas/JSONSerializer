package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayList;
import java.util.List;

public class ClassWithListOfSimpleObjects implements BaseObject {
    List<SimpleObject> listOfSimpleObjects = new ArrayList<>();

    public ClassWithListOfSimpleObjects() {
        for (int i = 0; i < 10; ++i) {
            listOfSimpleObjects.add(new SimpleObject("name" + i));
        }
    }
}
