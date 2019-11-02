package com.varlamovas.jsonserializer.seed;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public abstract class BaseSeed {

    public abstract Object spawn();

    JSONObject createJSONObjectByType(Type type) {
        Class<?> clazz;
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            clazz = (Class<?>) pType.getRawType();
        } else {
            clazz = (Class<?>) type;
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return new MapSeed(type);
        }
        return new ObjectSeed(type);
    }

    JSONArray createJSONArrayByType(Type type) {
        Class<?> clazz;
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            clazz = (Class<?>) pType.getRawType();
        } else {
            clazz = (Class<?>) type;
        }
        if (clazz.isArray()) {
            return new ArraySeed(type);
        }
        if (Collection.class.isAssignableFrom(clazz)) {
            return new CollectionSeed(type);
        }
        throw new IllegalStateException();
    }

}
