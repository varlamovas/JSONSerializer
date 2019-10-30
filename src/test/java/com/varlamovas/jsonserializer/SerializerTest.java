package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import com.varlamovas.jsonserializer.testobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {

    static JsonSerializer serializer;
    static Gson gsonSerializer;

    static Stream<Arguments> provideObjectsWithFieldWithDifferentAccessModificators() {
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
                Arguments.of(ClassWithMapOfStringToString.getInstance(), ClassWithMapOfStringToString.getJson()),
                Arguments.of(ClassWithMapOfNumberToNumber.getInstance(), ClassWithMapOfNumberToNumber.getJson()),
                Arguments.of(ClassWithMapOfStringToMapOfStringToNumber.getInstance(), ClassWithMapOfStringToMapOfStringToNumber.getJson()),
                Arguments.of(FooObject.getInstance(), FooObject.getJson()),
                Arguments.of(ClassWithListOfSimpleObjects.getInstance(), ClassWithListOfSimpleObjects.getJson()),
                Arguments.of(ClassWithArrayOfIntegersField.getInstance(), ClassWithArrayOfIntegersField.getJson())
        );
    }

    @BeforeEach
    void setUpTest() {
        serializer = new JsonSerializer();
        gsonSerializer = new Gson();
    }

    @ParameterizedTest
    @MethodSource("provideObjectsWithFieldWithDifferentAccessModificators")
    void serializeSimple(Object obj, String expectedResult){
        String actualResult = serializer.serialize(obj);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("provideObjectsWithFieldWithDifferentAccessModificators")
    void serializeWithGSON(Object obj, String expectedResult) {
        String actualResult = gsonSerializer.toJson(obj);
        assertEquals(expectedResult, actualResult);
    }

}
