package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.adapters.AdapterFactory;
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

public class ObjectSeed extends JSONObject {

    private Class<?> clazz;
    private Type type;
    private List<Field> allFields;
    private Object instance;
    private HashMap<String, Token> propMapSimple = new HashMap<>();
    private HashMap<String, BaseSeed> propMapComb = new HashMap<>();

    public ObjectSeed(Type type) {
        this.type = type;
        this.clazz = (Class<?>) type;
        this.allFields = FieldRetriever.getAllFields(clazz);
    }

    public Object newInstance() {
        instance = null;
        List<Constructor> constructors = Arrays.asList(clazz.getDeclaredConstructors());
        constructors.forEach(constructor -> constructor.setAccessible(true));
        try {
            try {
                instance = constructors.get(0).newInstance();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException e) {
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

    public JSONArray createJSONArray(String propName) {
        Type fieldType = getField(propName).getGenericType();
        JSONArray jsonArray = createJSONArrayByType(fieldType);
        propMapComb.put(propName, jsonArray);
        return jsonArray;
    }

    public JSONObject createJSONObject(String propName) {
        Type fieldType = getField(propName).getGenericType();
        JSONObject jsonObject = createJSONObjectByType(fieldType);
        propMapComb.put(propName, jsonObject);
        return jsonObject;
    }

    public void addProperty(String propertyName, Token token) {
        propMapSimple.put(propertyName, token);
    }

    public void addCombProperty(String propertyName, BaseSeed seed) {
        propMapComb.put(propertyName, seed);
    }

    public Object spawn() {
        instance = newInstance();
        for (Map.Entry<String, Token> entry : propMapSimple.entrySet()) {
            Field field = getField(entry.getKey());
            if (field.getType().isPrimitive()) {
                AdapterFactory.getPrimitiveAdapter().fromJson(field, instance, entry.getValue());
            } else {
                ObjectAdapter adapter = AdapterFactory.getAdapter(field.getType());
                assert adapter != null;
                Object obj = adapter.fromJson(entry.getValue());
                FieldRetriever.setFieldObject(field, instance, obj);
            }
        }
        for (Map.Entry<String, BaseSeed> entry : propMapComb.entrySet()) {
            Field field = getField(entry.getKey());
            BaseSeed seed = entry.getValue();
            FieldRetriever.setFieldObject(field, instance, seed.spawn());
        }
        return instance;
    }

}
