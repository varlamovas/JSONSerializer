package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.exceptions.MalformedJSONException;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectSeed extends JSONObject {

    private final Class<?> clazz;
    private final Type type;
    private final List<Field> allFields;
    private final Object instance;
    private Map<String, Token> propMapSimple = Collections.emptyMap();
    private Map<String, BaseSeed> propMapComb = Collections.emptyMap();

    public ObjectSeed(Type type) {
        this.type = type;
        this.clazz = (Class<?>) type;
        this.allFields = FieldRetriever.getAllFields(clazz);
        this.instance = newInstance();
    }

    public Object newInstance() {
        Object instance = null;
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
        for (Field field : findedField) {
            Annotation[] annotations = field.getAnnotations();
            AnnotatedType annotatedType = field.getAnnotatedType();
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        }
        if (findedField.isEmpty()) {
            //TODO: Think about it exception
            throw new MalformedJSONException("No one field were found by the name: " + name);
        }
        return findedField.get(0);
    }

    public JSONArray createJSONArray(String propName) {
        Type fieldType = getField(propName).getGenericType();
        JSONArray jsonArray = createJSONArrayByType(fieldType);
//        propMapComb.put(propName, jsonArray);
        return jsonArray;
    }

    public JSONObject createJSONObject(String propName) {
        Type fieldType = getField(propName).getGenericType();
        JSONObject jsonObject = createJSONObjectByType(fieldType);
//        propMapComb.put(propName, jsonObject);


        return jsonObject;
    }

    public void addProperty(String propertyName, Token token) {
//        if (propMapSimple.isEmpty()) {
//            propMapSimple = new HashMap<>();
//        }
//        propMapSimple.put(propertyName, token);
        Field field = getField(propertyName);
        if (Modifier.isTransient(field.getModifiers())) {
            return;
        }
        if (field.getType().isPrimitive()) {
            AdapterFactory.getPrimitiveAdapter().fromJson(field, instance, token);
        } else {
            ObjectAdapter adapter = AdapterFactory.getAdapter(field.getType());
            assert adapter != null;
            Object obj = adapter.fromJson(token);
            FieldRetriever.setFieldObject(field, instance, obj);
        }
    }

    public void addCombProperty(String propertyName, BaseSeed seed) {
//        if (propMapComb.isEmpty()) {
//            propMapComb = new HashMap<>();
//        }
//        propMapComb.put(propertyName, seed);
        Field field = getField(propertyName);
        FieldRetriever.setFieldObject(field, instance, seed.spawn());
    }

    public Object spawn() {
//        for (Map.Entry<String, Token> entry : propMapSimple.entrySet()) {
//            Field field = getField(entry.getKey());
//            if (field.getType().isPrimitive()) {
//                AdapterFactory.getPrimitiveAdapter().fromJson(field, instance, entry.getValue());
//            } else {
//                ObjectAdapter adapter = AdapterFactory.getAdapter(field.getType());
//                assert adapter != null;
//                Object obj = adapter.fromJson(entry.getValue());
//                FieldRetriever.setFieldObject(field, instance, obj);
//            }
//        }
//        for (Map.Entry<String, BaseSeed> entry : propMapComb.entrySet()) {
//            Field field = getField(entry.getKey());
//            BaseSeed seed = entry.getValue();
//            FieldRetriever.setFieldObject(field, instance, seed.spawn());
//        }
        return instance;
    }

}
