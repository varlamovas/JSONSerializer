package com.varlamovas.jsonserializer;

public class JsonSerializer {

    public static String serialize(Object object) {
        NewSerializer serializer = new NewSerializer(object);
//        Serializer serializer = new Serializer(object);
        return serializer.serialize();
    }

    public static <T> T deserialize(String json, Class<T> klass) {
        NewDeserializer<T> deserializer = new NewDeserializer<>(json, klass);
        return deserializer.deserialize();
    }
}
