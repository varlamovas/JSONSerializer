package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class CollectionSeed<T extends Collection> implements JSONArray {
    private Type type;
    private Class<T> clazz;
    private T instance;
    private List<BaseSeed> seeds = new ArrayList<>();

    private List<Token> tokens = new ArrayList<>();

    public CollectionSeed(Type type) {
        this.type = type;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.clazz = (Class<T>) parameterizedType.getRawType();
        newInstance();
    }

//    private Collection<> createInstance(Class<?> clazz) {
//        Collection<?> instance = null;
//
//        if (clazz.isInterface()) {
//            if (List.class.isAssignableFrom(clazz)) {
//                instance = new ArrayList<>();
//                return instance;
//            }
//        }
//
//        try {
//            instance = (Collection<?>) clazz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return instance;
//    }

    public T newInstance() {
        instance = null;
        Constructor<?>[] constructors = clazz.getConstructors();

        if (clazz.isInterface()) {
            if (List.class.isAssignableFrom(clazz)) {
                instance = (T) new ArrayList<>();
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

//    public CollectionSeed(Class<T> clazz) {
//        this(clazz, null);
//    }

    public void add(Token token) {
        tokens.add(token);
    }

    public void addComb(BaseSeed seed) {
        seeds.add(seed);
    }

    public Collection<?> getInstance() {
        return instance;
    }

    public T spawn() {
        Class<?> clazzInnerType;
        Type type = getInnerType();
        if (type instanceof ParameterizedType) {
            clazzInnerType = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            clazzInnerType = (Class<?>) type;
        }
        ObjectAdapter adapter = AdapterFactory.getAdapter(clazzInnerType);
        for (Token token : tokens) {
            Object obj = adapter.fromJson(token, this);
            instance.add(obj);
        }



        for (BaseSeed seed : seeds) {
            instance.add(seed.spawn());
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
        Type type = getInnerType();
        return new CollectionSeed<>(type);
    }

    @Override
    public JSONObject createNewObject() {
        Class<?> clazz;
        Type type = getInnerType();
        if (type instanceof ParameterizedType) {
            clazz = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            clazz = (Class<?>) type;
        }

        if (Map.class.isAssignableFrom(clazz)) {
            return new MapSeed(type);
        }
        return new ObjectSeed(clazz);
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
