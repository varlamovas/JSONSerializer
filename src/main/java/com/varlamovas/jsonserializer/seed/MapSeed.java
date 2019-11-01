package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapSeed<T extends Map> implements JSONObject {

    private T instance;
    private final Class<T> clazz;
    private final Type type;
    private final Map<String, BaseSeed> propToSeed = new HashMap<>();
    private final Map<String, Token> propToToken = new HashMap<>();

    public MapSeed(Type type) {
        this.type = type;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.clazz = (Class<T>) parameterizedType.getRawType();
        newInstance();
    }

    @Override
    public JSONObject createNewObject(String propertyName) {
        Type valueType = getInnerValueType();
        Class clazz = (Class) valueType;
        if (Map.class.isAssignableFrom(clazz)) {
            return new MapSeed(type);
        }
        return new ObjectSeed(clazz);
    }

    @Override
    public CollectionSeed createCollectionSeed(String name) {
        return null;
    }

    @Override
    public void addCombProperty(String propertyName, BaseSeed seed) {
        propToSeed.put(propertyName, seed);
    }

    @Override
    public void addProperty(String propertyName, Token token) {
        propToToken.put(propertyName, token);
    }

    @Override
    public T spawn() {
        instance = newInstance();

        Class<?> clazzInnerValueType;
        Type type = getInnerValueType();
        if (type instanceof ParameterizedType) {
            clazzInnerValueType = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            clazzInnerValueType = (Class<?>) type;
        }
        ObjectAdapter adapter = AdapterFactory.getAdapter(clazzInnerValueType);
        for (Map.Entry<String, Token> entry : propToToken.entrySet()) {

            assert adapter != null;
            Object obj = adapter.fromJson(entry.getKey(), entry.getValue(), this);
            instance.put(entry.getKey(), obj);
        }

        for (Map.Entry<String, BaseSeed> entry : propToSeed.entrySet()) {
            Object obj = entry.getValue().spawn();
            instance.put(entry.getKey(), obj);
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

    private T newInstance() {
        instance = null;
        if (clazz.isInterface()) {
            if (Map.class.isAssignableFrom(clazz)) {
                instance = (T) new HashMap<>();
                return instance;
            }
        }

        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private Type getInnerValueType() {
        assert type instanceof ParameterizedType;
        ParameterizedType paramType = (ParameterizedType) type;
        Type[] typeArgs = paramType.getActualTypeArguments();
        assert typeArgs.length == 2;
        Type innerType = typeArgs[1];
        return innerType;
    }

    public T getInstance() {
        assert instance != null;
        return instance;
    }
}
