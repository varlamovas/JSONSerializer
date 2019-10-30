package com.varlamovas.jsonserializer.testobjects;

public class ChildrenClassWithSetter extends ParentClassWithSetter implements BaseObject{
    String childrenSettedString;

    public void setChildrenSettedString(String str) {
        childrenSettedString = str;
    }

    public static ChildrenClassWithSetter getInstance() {
        ChildrenClassWithSetter childrenClassWithSetter = new ChildrenClassWithSetter();
        childrenClassWithSetter.setChildrenSettedString("childrenSettedStringValue");
        childrenClassWithSetter.setParentSettedString("parentSettedStringValue");
        return childrenClassWithSetter;
    }

    public static String getJson() {
        return "{\"childrenSettedString\":\"childrenSettedStringValue\",\"parentSettedString\":\"parentSettedStringValue\"}";
    }
}
