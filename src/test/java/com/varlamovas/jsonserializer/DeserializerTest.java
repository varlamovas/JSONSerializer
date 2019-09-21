package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class DeserializerTest {

    static JsonSerializer jsonSerializer;
    static Gson gsonSerializer;

    @BeforeEach
    void setUpTest() {
        jsonSerializer = new JsonSerializer();
        gsonSerializer = new Gson();
    }

    @Test
    void deserializeWithJsonserializer() {
        CustomClass fooObject = CustomClass.getInstance();
        String json = jsonSerializer.serialize(fooObject);
        System.out.println(json);
        CustomClass deserializedFooObject = jsonSerializer.deserialize(json, CustomClass.class);
        assertEquals(fooObject.stringField, deserializedFooObject.stringField);
        assertEquals(fooObject.byteFieldBoxed, deserializedFooObject.byteFieldBoxed);
        assertEquals(fooObject.shortFieldBoxed, deserializedFooObject.shortFieldBoxed);
        assertEquals(fooObject.charFieldBoxed, deserializedFooObject.charFieldBoxed);
        assertEquals(fooObject.intFieldBoxed, deserializedFooObject.intFieldBoxed);
        assertEquals(fooObject.longFieldBoxed, deserializedFooObject.longFieldBoxed);
        assertEquals(fooObject.floatFieldBoxed, deserializedFooObject.floatFieldBoxed);
        assertEquals(fooObject.doubleFieldBoxed, deserializedFooObject.doubleFieldBoxed);
        assertEquals(fooObject.booleanFieldBoxed, deserializedFooObject.booleanFieldBoxed);
    }

    @Test
    void deserializeWithGSON() {
        CustomClass fooObject = CustomClass.getInstance();
        String json = gsonSerializer.toJson(fooObject);
        System.out.println(json);
        CustomClass deserializedFooObject = gsonSerializer.fromJson(json, CustomClass.class);
        assertEquals(fooObject.stringField, deserializedFooObject.stringField);
        assertEquals(fooObject.byteFieldBoxed, deserializedFooObject.byteFieldBoxed);
        assertEquals(fooObject.shortFieldBoxed, deserializedFooObject.shortFieldBoxed);
        assertEquals(fooObject.charFieldBoxed, deserializedFooObject.charFieldBoxed);
        assertEquals(fooObject.intFieldBoxed, deserializedFooObject.intFieldBoxed);
        assertEquals(fooObject.longFieldBoxed, deserializedFooObject.longFieldBoxed);
        assertEquals(fooObject.floatFieldBoxed, deserializedFooObject.floatFieldBoxed);
        assertEquals(fooObject.doubleFieldBoxed, deserializedFooObject.doubleFieldBoxed);
        assertEquals(fooObject.booleanFieldBoxed, deserializedFooObject.booleanFieldBoxed);
    }

    @Test
    void deserializeSimple() {
        String json = "{\"stringField\":\"stringValue\"}";
        ClassSimple classSimple = new ClassSimple();
        classSimple.setStringField("stringValue");
        ClassSimple deserializeClassSimple = jsonSerializer.deserialize(json, ClassSimple.class);
        assertEquals(deserializeClassSimple.stringField, classSimple.stringField);
    }

    @Test
    void deserializeSimpleGSON() {
        String json = "{\"stringField\":\"stringValue\"}";
        ClassSimple classSimple = new ClassSimple();
        classSimple.setStringField("stringValue");
        ClassSimple deserializeClassSimple = gsonSerializer.fromJson(json, ClassSimple.class);
        assertEquals(deserializeClassSimple.stringField, classSimple.stringField);
    }

    @Test
    void deserializeWith_ClassWithArrayListOfStringField() {
        ClassWithArrayListOfStringField instance = ClassWithArrayListOfStringField.getInstance();
        String json = jsonSerializer.serialize(instance);
        ClassWithArrayListOfStringField deserialized = jsonSerializer.deserialize(json, ClassWithArrayListOfStringField.class);

        assertIterableEquals(instance.listOfStrings, deserialized.listOfStrings);
    }

    @Test
    void GSON_deserializeWith_ClassWithArrayListOfStringField() {
        ClassWithListOfStringField instance = ClassWithListOfStringField.getInstance();
        String json = gsonSerializer.toJson(instance);
        ClassWithListOfStringField deserialized = gsonSerializer.fromJson(json, ClassWithListOfStringField.class);

        assertIterableEquals(instance.listOfStrings, deserialized.listOfStrings);
    }
}

class ClassSimple {

    String stringField;
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }
}

class CustomClass {

    String stringField;
    Byte byteFieldBoxed;
    Short shortFieldBoxed;
    Character charFieldBoxed;
    Integer intFieldBoxed;
    Long longFieldBoxed;
    Float floatFieldBoxed;
    Double doubleFieldBoxed;
    Boolean booleanFieldBoxed;

    public void setAllFIelds() {
        stringField = "stringFieldValue";
        byteFieldBoxed = 127;
        shortFieldBoxed = 32767;
        charFieldBoxed = 'c';
        intFieldBoxed = 2147483647;
        longFieldBoxed = 9223372036854775807L;
        floatFieldBoxed = 3.4e+38f;
        doubleFieldBoxed = 1.7e+308;
        booleanFieldBoxed = true;
    }

    public static CustomClass getInstance() {
        CustomClass instance = new CustomClass();
        instance.setAllFIelds();
        return instance;
    }
}