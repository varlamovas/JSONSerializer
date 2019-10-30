package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import com.varlamovas.jsonserializer.testobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class DeserializerTest {

    static JsonSerializer jsonSerializer;
    static Gson gsonSerializer;

    static Stream<Arguments> provideInstancesAndJsons() {
        return Stream.of(
                Arguments.of(ClassOnlyWithPublicFields.getInstance(), ClassOnlyWithPublicFields.getJson()),
                Arguments.of(ClassOnlyWithPackagePrivateFields.getInstance(), ClassOnlyWithPackagePrivateFields.getJson()),
                Arguments.of(ClassOnlyWithProtectedFields.getInstance(), ClassOnlyWithProtectedFields.getJson()),
                Arguments.of(ClassOnlyWithPrivateFields.getInstance(), ClassOnlyWithPrivateFields.getJson()),
                Arguments.of(ClassOnlyWithFinalFields.getInstance(), ClassOnlyWithFinalFields.getJson()),
                Arguments.of(ClassOnlyWithStaticFields.getInstance(), ClassOnlyWithStaticFields.getJson()),
                Arguments.of(Children.getInstance(), Children.getJson()),
                Arguments.of(ChildrenClassWithSetter.getInstance(), ChildrenClassWithSetter.getJson()),
                Arguments.of(ClassWithSetter.getInstance(), ClassWithSetter.getJson()),
                Arguments.of(ClassWithArrayListOfStringField.getInstance(), ClassWithArrayListOfStringField.getJson()),
                Arguments.of(ClassWithListOfListOfStringsField.getInstance(), ClassWithListOfListOfStringsField.getJson()),
                Arguments.of(ClassWithSetOfStringsField.getInstance(), ClassWithSetOfStringsField.getJson()),
                Arguments.of(ClassWithQueueOfStringsField.getInstance(), ClassWithQueueOfStringsField.getJson()),
                Arguments.of(ClassWithMapOfStringToString_defaultField.getInstance(), ClassWithMapOfStringToString_defaultField.getJson()),
                Arguments.of(ClassWithMapOfNumberToNumber.getInstance(), ClassWithMapOfNumberToNumber.getJson()),
                Arguments.of(ClassWithMapOfStringToMapOfStringToNumber.getInstance(), ClassWithMapOfStringToMapOfStringToNumber.getJson()),
                Arguments.of(FooObject.getInstance(), FooObject.getJson()),
                Arguments.of(ClassWithListOfSimpleObjects.getInstance(), ClassWithListOfSimpleObjects.getJson()),
                Arguments.of(ClassWithArrayOfIntegersField.getInstance(), ClassWithArrayOfIntegersField.getJson())
        );
    }

    @BeforeEach
    void setUpTest() {
        jsonSerializer = new JsonSerializer();
        gsonSerializer = new Gson();
    }

    @ParameterizedTest
    @MethodSource("provideInstancesAndJsons")
    void defaultTest(Object obj, String json) {
        assert true;
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
        ClassWithListOfSimpleObjects deserialized = jsonSerializer.deserialize(ClassWithListOfSimpleObjects.getJson(), ClassWithListOfSimpleObjects.class);
        assertIterableEquals(initial.listOfSimpleObjects, deserialized.listOfSimpleObjects);
    }

    @Test
    void deserialize__ClassWithListOfListOfStringsField() {
        ClassWithListOfListOfStringsField initial = ClassWithListOfListOfStringsField.getInstance();
        ClassWithListOfListOfStringsField deserialized = jsonSerializer.deserialize(ClassWithListOfListOfStringsField.getJson(), ClassWithListOfListOfStringsField.class);
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
    void deserializeWith_ClassWithMapOfStringToString_defaultField() {
        ClassWithMapOfStringToString_defaultField instance = ClassWithMapOfStringToString_defaultField.getInstance();
        String json = jsonSerializer.serialize(instance);
        ClassWithMapOfStringToString_defaultField deserialized = jsonSerializer.deserialize(json, ClassWithMapOfStringToString_defaultField.class);
        assertIterableEquals(instance.mapOfStringToString.keySet(), deserialized.mapOfStringToString.keySet());
        assertIterableEquals(instance.mapOfStringToString.values(), deserialized.mapOfStringToString.values());
    }

    @Test
    void deserializeWith_ClassWithMapOfStringToString() {
        ClassWithMapOfStringToString instance = ClassWithMapOfStringToString.getInstance();
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