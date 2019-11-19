package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.annotations.CustomName;
import com.varlamovas.jsonserializer.testobjects.ClassWithAnotatedField;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Date;

public class AnnotationsTest {

    @Test
    void annotationsTest() {
        ClassWithAnotatedField instance = new ClassWithAnotatedField();
        Field[] declaredFields = instance.getClass().getDeclaredFields();
        Field declaredField = declaredFields[0];
        Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
        Annotation[] annotations = declaredField.getAnnotations();
        AnnotatedType annotatedType = declaredField.getAnnotatedType();

        if (declaredField.isAnnotationPresent(CustomName.class)) {
            CustomName ann = declaredField.getAnnotation(CustomName.class);
            String nmae= ann.name();
            System.out.println(nmae);
        }

        Annotation annotation = annotations[0];;

        JsonSerializer js = new JsonSerializer();

        ClassWithDateField inst = ClassWithDateField.getInstance();
        String json = js.serialize(inst);
        System.out.println(json);

    }
}

class ClassWithDateField {

    @CustomName(name = "bla") String datefield;

    public static ClassWithDateField getInstance() {
        ClassWithDateField instance = new ClassWithDateField();
        instance.setFields();
        return instance;
    }

    private void setFields() {
        datefield = "fooFoo";
    }
}