package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayList;
import java.util.List;

public class ClassWithListOfSimpleObjects implements BaseObject {
    public ArrayList<SimpleObject> listOfSimpleObjects;

    void setListOfSimpleObjects() {
        listOfSimpleObjects = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            listOfSimpleObjects.add(SimpleObject.getInstance("name" + i));
        }
    }

    public static ClassWithListOfSimpleObjects getInstance() {
        ClassWithListOfSimpleObjects obj = new ClassWithListOfSimpleObjects();
        obj.setListOfSimpleObjects();
        return obj;
    }

    public static String getJson() {
        return "{\"listOfSimpleObjects\":[{\"field\":\"name0\"},{\"field\":\"name1\"},{\"field\":\"name2\"},{\"field\":\"name3\"},{\"field\":\"name4\"},{\"field\":\"name5\"},{\"field\":\"name6\"},{\"field\":\"name7\"},{\"field\":\"name8\"},{\"field\":\"name9\"}]}";
    }
}
