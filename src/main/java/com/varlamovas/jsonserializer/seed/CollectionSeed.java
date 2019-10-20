package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.FieldRetriever;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class CollectionSeed<T>  extends BaseSeed{
    private Class<T> clazz;
    private Type type;
    private List<Field> allFields;
    private T instance;
    private HashMap<String, Object> propMap = new HashMap<>();

    public CollectionSeed(Class<T> clazz, Type type) {
        this.clazz = clazz;
        this.type = type;
        this.allFields = null;
    }

    public CollectionSeed(Class<T> clazz) {
        this(clazz, null);
    }

    public T newInstance() {
        return null;
    }

    public List<Field> getAllFields() {
        return allFields;
    }

}
