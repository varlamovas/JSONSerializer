package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.exceptions.FieldRetrieveException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldRetriever {

    public static List<Field> getAllFields(Object obj) {
        Class<?> clazz = obj.getClass();
        return getAllFields(clazz);
    }

    public static <T> List<Field> getAllFields(Class<T> clazz) {
        List<Field> declaredFields = Arrays.stream(clazz.getDeclaredFields()).filter(f -> !Modifier.isStatic(f.getModifiers())).collect(Collectors.toList());
        List<Field> allSuperClassesFields = getAllSuperClassesFields(clazz);
        declaredFields.addAll(allSuperClassesFields);
        declaredFields.forEach((field) -> field.setAccessible(true));
        return declaredFields;
    }

    public static <T> List<Field> getAllSuperClassesFields(Class<T> clazz) {
        Stream<Field> allSuperClassesFields = Stream.empty();
        List<Class> allSuperClasses = getAllSuperClasses(clazz);
        for (Class superClazz : allSuperClasses) {
            allSuperClassesFields = Stream.concat(allSuperClassesFields, Arrays.stream(superClazz.getDeclaredFields()).filter(f -> !Modifier.isStatic(f.getModifiers())));
        }
        return allSuperClassesFields.collect(Collectors.toList());
    }

    public static <T> List<Class> getAllSuperClasses(Class<T> clazz) {
        List<Class> superClasses = new ArrayList<>();
        Class currentClass = clazz;
        while (true) {
            currentClass = currentClass.getSuperclass();
            if (currentClass == Object.class) {
                break;
            }
            superClasses.add(currentClass);
        }
        return superClasses;
    }

    public static Object getFieldObject(Field field, Object objectWithField) {
        Object instance = null;
        try {
            instance = field.get(objectWithField);
        } catch (IllegalAccessException e) {
            //TODO: need to clarify exception and message
            throw new FieldRetrieveException();
        }
        return instance;
    }

    public static void setFieldObject(Field field, Object instance, Object fieldObject) {
        try {
            field.set(instance, fieldObject);
        } catch (IllegalAccessException e) {
            //TODO: need to clarify exception and message
            throw new FieldRetrieveException();
        }
    }
}
