package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import com.varlamovas.jsonserializer.testobjects.*;
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
    void deserializeClassWithListOfSimpleObjects() {
        ClassWithListOfSimpleObjects initial = ClassWithListOfSimpleObjects.getInstance();
        ClassWithListOfSimpleObjects deserialized = jsonSerializer.deserialize(ClassWithListOfSimpleObjects.toJson(), ClassWithListOfSimpleObjects.class);
        assertIterableEquals(initial.listOfSimpleObjects, deserialized.listOfSimpleObjects);
    }

    @Test
    void deserialize__ClassWithListOfListOfStringsField() {
        ClassWithListOfListOfStringsField initial = ClassWithListOfListOfStringsField.getInstance();
        ClassWithListOfListOfStringsField deserialized = jsonSerializer.deserialize(ClassWithListOfListOfStringsField.toJson(), ClassWithListOfListOfStringsField.class);
        assertIterableEquals(initial.listOfListOfStrings, deserialized.listOfListOfStrings);
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
    void deserializeWith_ClassWithMapOfStringToString() {
        ClassWithMapOfStringToString instance = new ClassWithMapOfStringToString();
        String json = jsonSerializer.serialize(instance);
        ClassWithMapOfStringToString deserialized = jsonSerializer.deserialize(json, ClassWithMapOfStringToString.class);
        assertIterableEquals(instance.mapOfStringToString.keySet(), deserialized.mapOfStringToString.keySet());
        assertIterableEquals(instance.mapOfStringToString.values(), deserialized.mapOfStringToString.values());
    }

    @Test
    void GSON_deserializeWith_ClassWithArrayListOfStringField() {
        ClassWithListOfStringField instance = ClassWithListOfStringField.getInstance();
        String json = gsonSerializer.toJson(instance);
        ClassWithListOfStringField deserialized = gsonSerializer.fromJson(json, ClassWithListOfStringField.class);

        assertIterableEquals(instance.listOfStrings, deserialized.listOfStrings);
    }
}