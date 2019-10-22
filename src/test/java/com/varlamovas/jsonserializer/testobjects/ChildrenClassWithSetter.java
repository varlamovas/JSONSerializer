package com.varlamovas.jsonserializer.testobjects;

public class ChildrenClassWithSetter extends ParentClassWithSetter implements BaseObject{
    String childrenSettedString;

    public void setChildrenSettedString(String str) {
        childrenSettedString = str;
    }

    public static ChildrenClassWithSetter getInstanceOfChildrenClassWithSetters() {
        ChildrenClassWithSetter childrenClassWithSetter = new ChildrenClassWithSetter();
        childrenClassWithSetter.setChildrenSettedString("childrenSettedStringValue");
        childrenClassWithSetter.setParentSettedString("parentSettedStringValue");
        return childrenClassWithSetter;
    }
}
