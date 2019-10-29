package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class CollectionSeed<T extends Collection> implements ArraySeed<T> {
    private Type type;
    private List<Field> allFields;
    private Class<T> clazz;
    private T instance;
    private List<BaseSeed> seeds = new ArrayList<>();

    private List<Token> tokens = new ArrayList<>();

    public CollectionSeed(Class<T> clazz, Type type) {
        this.clazz = clazz;
        this.type = type;
        this.allFields = null;
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
            adapter.fromJson(token, this);
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
    public ArraySeed<?> createCollectionSeed() {
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
    public PropertyValueSeed<?> createNewObject() {
        Class<?> clazz;
        Type type = getInnerType();
        if (type instanceof ParameterizedType) {
            clazz = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            clazz = (Class<?>) type;
        }

        if (Map.class.isAssignableFrom(clazz)) {
            Class<? extends Map> klass = (Class<? extends Map>) ((ParameterizedType) type).getRawType();
            return new MapSeed(klass, type);
        }
        return new ObjectSeed<>(clazz);
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
