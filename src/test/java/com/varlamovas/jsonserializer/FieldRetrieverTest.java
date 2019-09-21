package com.varlamovas.jsonserializer;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldRetrieverTest {


    @Test
    void testGetAllSuperClasses(){
        DeepChildren deepChildren = new DeepChildren();
        List<Class> listOfSuperClasses = FieldRetriever.getAllSuperClasses(deepChildren.getClass());
//        System.out.println(listOfSuperClasses.toString());
        assertEquals("[class com.varlamovas.jsonserializer.ParentFour, class com.varlamovas.jsonserializer.ParentThree, class com.varlamovas.jsonserializer.ParentTwo, class com.varlamovas.jsonserializer.ParentOne]",
                listOfSuperClasses.toString());
    }

    @Test
    void testGetAllSuperClassesFields(){
        DeepChildren deepChildren = new DeepChildren();
        List<Field> listAllSuperClassesFields = FieldRetriever.getAllSuperClassesFields(deepChildren.getClass());
        assertEquals("[public java.lang.String com.varlamovas.jsonserializer.ParentFour.stringParentFour, public java.lang.String com.varlamovas.jsonserializer.ParentThree.stringParentThree, public java.lang.String com.varlamovas.jsonserializer.ParentTwo.stringParentTwo, public java.lang.String com.varlamovas.jsonserializer.ParentOne.stringParentOne]",
                listAllSuperClassesFields.toString());
    }
}

class ParentOne{
    public String stringParentOne = "stringParentOneValue";
}
class ParentTwo extends ParentOne{
    public String stringParentTwo = "stringParentTwoValue";
}
class ParentThree extends ParentTwo{
    public String stringParentThree = "stringParentThreeValue";
}
class ParentFour extends ParentThree{
    public String stringParentFour = "stringParentFourValue";
}
class DeepChildren extends ParentFour{
    public String stringDeepChildren = "stringDeepChildren";
}