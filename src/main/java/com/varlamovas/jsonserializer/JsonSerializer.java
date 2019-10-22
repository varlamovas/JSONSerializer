package com.varlamovas.jsonserializer;

public class JsonSerializer {

    public static String serialize(Object object) {
        Serializer serializer = new Serializer(object);
        return serializer.serialize();
    }

    public static <T> T deserialize(String json, Class<T> klass) {
        Deserializer<T> deserializer = new Deserializer<>(json, klass);
        return deserializer.deserialize();
    }
}
