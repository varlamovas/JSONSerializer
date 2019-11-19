package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapSeed extends JSONObject {

    private Map<Object, Object> instance;
    private final Class<?> clazz;
    private final Type type;
    private final Map<String, BaseSeed> propToSeed = new HashMap<>();
    private final Map<String, Token> propToToken = new HashMap<>();

    public MapSeed(Type type) {
        this.type = type;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.clazz = (Class<?>) parameterizedType.getRawType();
        newInstance();
    }

    @Override
    public JSONObject createJSONObject(String propertyName) {
        Type valueType = getInnerValueType();
        JSONObject jsonObject = createJSONObjectByType(valueType);
//        propToSeed.put(propertyName, jsonObject);
        return jsonObject;
    }

    @Override
    public JSONArray createJSONArray(String propertyName) {
        Type valueType = getInnerValueType();
        JSONArray jsonArray = createJSONArrayByType(valueType);
//        propToSeed.put(propertyName, jsonArray);
        return jsonArray;
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
    public Map<Object, Object> spawn() {
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
            Object obj = adapter.fromJson(entry.getValue());
            instance.put(entry.getKey(), obj);
        }

        for (Map.Entry<String, BaseSeed> entry : propToSeed.entrySet()) {
            Object obj = entry.getValue().spawn();
            instance.put(entry.getKey(), obj);
        }
        return instance;
    }

    private Map<Object, Object> newInstance() {
        instance = null;
        if (clazz.isInterface()) {
            if (Map.class.isAssignableFrom(clazz)) {
                instance = new HashMap<>();
                return instance;
            }
        }

        try {
            instance = (Map<Object, Object>) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
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

    public Map<Object, Object> getInstance() {
        assert instance != null;
        return instance;
    }
}
