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

    public static ClassWithListOfSimpleObjects getInstance() {
        return new ClassWithListOfSimpleObjects();
    }

    public static String toJson() {
        return "{\"listOfSimpleObjects\":[{\"field\":\"name0\"},{\"field\":\"name1\"},{\"field\":\"name2\"},{\"field\":\"name3\"},{\"field\":\"name4\"},{\"field\":\"name5\"},{\"field\":\"name6\"},{\"field\":\"name7\"},{\"field\":\"name8\"},{\"field\":\"name9\"}]}";
    }
}
