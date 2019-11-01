package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.ArrayWrapper;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ArraySeed implements JSONArray {

    private Type type;
    private List<Field> allFields;
    private Class<?> clazz;
    private Object instance;
    private List<BaseSeed> seeds = new ArrayList<>();

    private List<Token> tokens = new ArrayList<>();

    public ArraySeed(Type type) {
        this.type = type;
        this.clazz = (Class<?>) type;
        this.allFields = null;
    }

    public Object newInstance() {
        instance = null;
        int arraySize = (tokens.size() == 0) ? seeds.size() : tokens.size();
        instance = Array.newInstance(clazz.getComponentType(), arraySize);
        return instance;
    }

//    public CollectionSeed(Class<T> clazz) {
//        this(clazz, null);
//    }

    public void add(Token token) {
        tokens.add(token);
    }

    public void addComb(BaseSeed seed) {
        seeds.add(seed);
    }

    public Object spawn() {
        newInstance();
        ArrayWrapper arrayWrappper = new ArrayWrapper(instance);
        for (int i = 0; i < tokens.size(); i++) {
            arrayWrappper.setValue(tokens.get(i), i);

        }

        for (int i = 0; i < seeds.size(); i++) {
            arrayWrappper.setValue(seeds.get(i).spawn(), i);
        }
        return instance;
    }

    @Override
    public boolean isPropertyValue() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public JSONArray createCollectionSeed() {
        Class<Collection> clazz;
        Type type = getInnerType();
        if (type instanceof ParameterizedType) {
            clazz = (Class<Collection>) ((ParameterizedType) type).getRawType();
        } else {
            clazz = (Class<Collection>) type;
        }
        return new CollectionSeed<>(clazz, type);
    }

    @Override
    public JSONObject createNewObject() {
        Class<?> klass;
        Type type = clazz.getComponentType();
        if (type instanceof ParameterizedType) {
            klass = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            klass = (Class<?>) type;
        }

        if (Map.class.isAssignableFrom(klass)) {
            Class<? extends Map> klazz = (Class<? extends Map>) ((ParameterizedType) type).getRawType();
            return new MapSeed(klazz, type);
        }
        return new ObjectSeed(klass);
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
