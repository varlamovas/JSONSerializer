package com.varlamovas.jsonserializer;

import java.lang.reflect.Field;
import java.util.List;

public class Seed<T> {

    private Class<T> clazz;
    private List<Field> allFields;
    private T instance;

    Seed(Class<T> clazz) {
        this.clazz = clazz;
        this.allFields = FieldRetriever.getAllFields(clazz);
    }

    public List<Field> getAllFields() {
        return allFields;
    }

    public T newInstance() {
        instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

//    public <T> Class<T> getClazz() {
//        return clazz;
//    }
}
