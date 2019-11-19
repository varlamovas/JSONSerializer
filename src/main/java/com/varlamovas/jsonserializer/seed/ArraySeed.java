package com.varlamovas.jsonserializer.seed;

import com.varlamovas.jsonserializer.ArrayWrapper;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ArraySeed extends JSONArray {

    private Type type;
    private Class<?> clazz;
    private Object instance;

    private List<BaseSeed> seeds = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();

    public ArraySeed(Type type) {
        this.type = type;
        this.clazz = (Class<?>) type;
    }

    public Object newInstance() {
        instance = null;
        int arraySize = (tokens.size() == 0) ? seeds.size() : tokens.size();
        instance = Array.newInstance(clazz.getComponentType(), arraySize);
        return instance;
    }

    public void add(Token token) {
        tokens.add(token);
    }

    public void addComb(BaseSeed seed) {
        seeds.add(seed);
    }

    @Override
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
    public JSONArray createJSONArray() {
        Type type = clazz.getComponentType();
        JSONArray jsonArray = createJSONArrayByType(type);
//        seeds.add(jsonArray);
        return jsonArray;
    }

    @Override
    public JSONObject createJSONObject() {
        Type type = clazz.getComponentType();
        JSONObject jsonObject = createJSONObjectByType(type);
//        seeds.add(jsonObject);
        return jsonObject;
    }

}
