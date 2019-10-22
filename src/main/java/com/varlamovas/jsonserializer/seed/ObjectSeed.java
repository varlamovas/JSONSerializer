package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.tokens.Token;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectSeed<T> extends BaseSeed{

    private Class<T> clazz;
    private Type type;
    private List<Field> allFields;
    private T instance;
    private HashMap<String, Object> propMap = new HashMap<>();

    public ObjectSeed(Class<T> clazz, Type type) {
        this.clazz = clazz;
        this.type = type;
        this.allFields = FieldRetriever.getAllFields(clazz);
    }

    public ObjectSeed(Class<T> clazz) {
        this(clazz, null);
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

    public Field getField(String name) {
        List<Field> findedField = allFields.stream().filter(f -> f.getName().equals(name)).collect(Collectors.toList());
        if (findedField.isEmpty()) {
            //TODO: Think about it exception
            throw new MalformedJSONException("No one field were found by the name: " + name);
        }
        return findedField.get(0);
    }

    public BaseSeed createNewArrayObject(String propName) {
        Class<?> clazz = getField(propName).getType();
        Type type = getField(propName).getGenericType();
        return new CollectionSeed(clazz, type);
    }

    public BaseSeed createNewObject(String propName) {
        Class<?> clazz = getField(propName).getClass();
        return new ObjectSeed(clazz);
    }

    public void addProperty(String propertyName, Token token) {
        ObjectAdapter adapter = AdapterFactory.getAdapter(null);
        Object obj = null;
//        Object obj = adapter.fromJson(token);
        propMap.put(propertyName, obj);
    }

    public T spawn() {
        instance = newInstance();
        for (Map.Entry<String, Object> entry: propMap.entrySet()) {
            Field field = getField(entry.getKey());
            try {
                field.set(instance, entry.getValue());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
