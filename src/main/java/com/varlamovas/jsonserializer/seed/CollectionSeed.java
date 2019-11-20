package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class CollectionSeed extends JSONArray {
    private Type type;
    private Class<?> clazz;
    private Collection<Object> instance;

    private List<BaseSeed> seeds = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();

    public CollectionSeed(Type type) {
        this.type = type;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.clazz = (Class<?>) parameterizedType.getRawType();
        this.instance = newInstance();
    }

    @SuppressWarnings("unchecked")
    public Collection<Object> newInstance() {
        instance = null;
        Constructor<?>[] constructors = clazz.getConstructors();

        if (clazz.isInterface()) {
            if (List.class.isAssignableFrom(clazz)) {
                instance = new ArrayList<>();
                return instance;
            }
        }

        try {
            instance = (Collection<Object>) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public void add(Token token) {
        Class<?> clazzInnerType;
        Type type = getInnerType();
        if (type instanceof ParameterizedType) {
            clazzInnerType = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            clazzInnerType = (Class<?>) type;
        }
        ObjectAdapter adapter = AdapterFactory.getAdapter(clazzInnerType);
        assert adapter != null;
        Object obj = adapter.fromJson(token);
        instance.add(obj);
    }

    public void addComb(BaseSeed seed) {
//        seeds.add(seed);
        instance.add(seed.spawn());
    }

    public Collection<Object> getInstance() {
        return instance;
    }

    public Collection<Object> spawn() {
//        Class<?> clazzInnerType;
//        Type type = getInnerType();
//        if (type instanceof ParameterizedType) {
//            clazzInnerType = (Class<?>) ((ParameterizedType) type).getRawType();
//        } else {
//            clazzInnerType = (Class<?>) type;
//        }
//        ObjectAdapter adapter = AdapterFactory.getAdapter(clazzInnerType);
//        for (Token token : tokens) {
//            assert adapter != null;
//            Object obj = adapter.fromJson(token);
//            instance.add(obj);
//        }
//        for (BaseSeed seed : seeds) {
//            instance.add(seed.spawn());
//        }
        return instance;
    }

    @Override
    public JSONArray createJSONArray() {
        Type type = getInnerType();
        JSONArray jsonArray = createJSONArrayByType(type);
//        seeds.add(jsonArray);
        return jsonArray;
    }

    @Override
    public JSONObject createJSONObject() {
        Type type = getInnerType();
        JSONObject jsonObject = createJSONObjectByType(type);
//        seeds.add(jsonObject);
        return jsonObject;
    }

    private Type getInnerType() {
        assert type instanceof ParameterizedType;
        ParameterizedType paramType = (ParameterizedType) type;
        Type[] typeArgs = paramType.getActualTypeArguments();
        assert typeArgs.length == 1;
        Type innerType = typeArgs[0];
        return innerType;
    }

}
