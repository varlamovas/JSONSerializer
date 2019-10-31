package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.BaseAdapter;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectSeed<T> implements PropertyValueSeed<T>{

    private Class<T> clazz;
    private Type type;
    private List<Field> allFields;
    private T instance;
    private HashMap<String, Token> propMapSimple = new HashMap<>();
    private HashMap<String, BaseSeed> propMapComb = new HashMap<>();

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
        List<Constructor> constructors = Arrays.asList(clazz.getDeclaredConstructors());
        constructors.forEach(constructor -> constructor.setAccessible(true));
        try {
            try {
                instance = (T) constructors.get(0).newInstance();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
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

    public ArraySeed<?> createCollectionSeed(String propName) {
        Class<?> clazz = getField(propName).getType();
        Type type = getField(propName).getGenericType();
        return new CollectionSeed(clazz, type);
    }

    public PropertyValueSeed createNewObject(String propName) {
        Class<?> clazz = getField(propName).getType();
        Type type = getField(propName).getGenericType();
        if (Map.class.isAssignableFrom(clazz)) {
            return new MapSeed(clazz, type);
        }
        return new ObjectSeed(clazz);
    }

    public void addProperty(String propertyName, Token token) {
        propMapSimple.put(propertyName, token);
    }

    public void addCombProperty(String propertyName, BaseSeed seed) {
        propMapComb.put(propertyName, seed);
    }

    public T spawn() {
        instance = newInstance();
        for (Map.Entry<String, Token> entry: propMapSimple.entrySet()) {
            Field field = getField(entry.getKey());
            ObjectAdapter adapter = AdapterFactory.getAdapter(field.getType());
            assert adapter != null;
            Object obj = adapter.fromJson(entry.getValue(), field, instance);
            FieldRetriever.setFieldObject(field, instance, obj);
        } for (Map.Entry<String, BaseSeed> entry: propMapComb.entrySet()) {
            Field field = getField(entry.getKey());
            BaseSeed seed = entry.getValue();
            FieldRetriever.setFieldObject(field, instance, seed.spawn());
        }
        return instance;
    }

    @Override
    public boolean isPropertyValue() {
        return true;
    }

    @Override
    public boolean isCollection() {
        return false;
    }
}
