package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CollectionSeed<T extends Collection> extends BaseSeed{
    private Type type;
    private List<Field> allFields;
    private HashMap<String, Object> propMap = new HashMap<>();
    private Class<T> clazz;
    private T instance;

    private List<Token> tokens = new ArrayList<>();

    public CollectionSeed(Class<T> clazz, Type type) {
        this.clazz = clazz;
        this.type = type;
        this.allFields = null;
        newInstance();
    }

    public Collection<?> newInstance() {
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

//    public CollectionSeed(Class<T> clazz) {
//        this(clazz, null);
//    }

    public void add(Token token) {
        tokens.add(token);
    }

    public Collection getInstance() {
        return instance;
    }

    public T spawn() {
        assert type instanceof ParameterizedType;
        ParameterizedType paramType = (ParameterizedType) type;
        Type[] typeArgs = paramType.getActualTypeArguments();
        assert typeArgs.length == 1;
        Type innerType = typeArgs[0];
        Class<?> clazzInnerType = (Class<?>) innerType;
        String typeName = innerType.getTypeName();
        ObjectAdapter adapter = AdapterFactory.getAdapter(clazzInnerType);
        for (Token token : tokens) {
            adapter.fromJson(token, this);
        }
        return instance;
    }

    @Override
    public void addProperty(String propertyName, Token token) {

    }

    @Override
    public CollectionSeed createCollectionSeed(String name) {
        return null;
    }

    @Override
    public void addCombProperty(String propertyName, BaseSeed seed) {

    }
}
