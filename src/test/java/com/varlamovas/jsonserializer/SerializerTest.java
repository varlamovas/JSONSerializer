package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import com.varlamovas.jsonserializer.testobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {

    static JsonSerializer serializer;
    static Gson gsonSerializer;

    static Stream<Arguments> provideObjectsWithFieldWithDifferentAccessModificators() {
        return Stream.of(
                Arguments.of(new ClassOnlyWithPublicFields(), "{\"stringField\":\"stringFieldValue\",\"intField\":42}"),
                Arguments.of(new ClassOnlyWithPackagePrivateFields(), "{\"stringField\":\"stringFieldValue\",\"intField\":42}"),
                Arguments.of(new ClassOnlyWithProtectedFields(), "{\"stringField\":\"stringFieldValue\",\"intField\":42}"),
                Arguments.of(new ClassOnlyWithPrivateFields(), "{\"stringField\":\"stringFieldValue\",\"intField\":42}"),
                Arguments.of(new ClassOnlyWithFinalFields(), "{\"stringField\":\"stringFieldValue\",\"intField\":42}"),
                Arguments.of(new ClassOnlyWithStaticFields(), "{}"),
                Arguments.of(new Children(), "{\"childrenPublicString\":\"childrenPublicStringValue\",\"childrenPackagePrivateString\":\"childrenPackagePrivateStringValue\",\"childrenProtectedString\":\"childrenProtectedStringValue\",\"childrenPrivateString\":\"childrenPrivateStringValue\",\"finalChildrenPublicString\":\"finalChildrenPublicString\",\"finalChildrenPackagePrivateString\":\"finalChildrenPackagePrivateString\",\"finalChildrenProtectedString\":\"finalChildrenProtectedString\",\"finalChildrenPrivateString\":\"finalChildrenPrivateString\",\"parentPublicString\":\"parentPublicStringValue\",\"parentPackagePrivateString\":\"parentPackagePrivateStringValue\",\"parentProtectedString\":\"parentProtectedStringValue\",\"parentPrivateString\":\"parentPrivateStringValue\",\"finalParentPublicString\":\"finalParentPublicString\",\"finalParentPackagePrivateString\":\"finalParentPackagePrivateString\",\"finalParentProtectedString\":\"finalParentProtectedString\",\"finalParentPrivateString\":\"finalParentPrivateString\"}"),
                Arguments.of(ChildrenClassWithSetter.getInstanceOfChildrenClassWithSetters(), "{\"childrenSettedString\":\"childrenSettedStringValue\",\"parentSettedString\":\"parentSettedStringValue\"}"),
                Arguments.of(ClassWithSetter.getInstanceOfClassWithSetters(), "{\"settedString\":\"settedStringValue\"}"),
                Arguments.of(ClassWithArrayListOfStringField.getInstance(), "{\"listOfStrings\":[\"list\",\"Of\",\"Strings\"]}"),
                Arguments.of(new ClassWithListOfListOfStringsField(), "{\"listOfListOfStrings\":[[\"first\",\"list\",\"Of\",\"Strings\"],[\"second\",\"list\",\"Of\",\"Strings\"]]}"),
                Arguments.of(new ClassWithSetOfStringsField(), "{\"setOfStrings\":[\"set\",\"Of\",\"Strings\"]}"),
                Arguments.of(new ClassWithQueueOfStringsField(), "{\"queueOfStrings\":[\"queue\",\"Of\",\"Strings\"]}"),
                Arguments.of(new ClassWithMapOfStringToString(), "{\"mapOfStringToString\":{\"firstKey\":\"firstValue\",\"secondKey\":\"secondValue\"}}"),
                Arguments.of(new ClassWithMapOfNumberToNumber(), "{\"mapOfStringToString\":{\"1\":1,\"2\":2}}"),
                Arguments.of(new ClassWithMapOfStringToMapOfStringToNumber(), "{\"mapOfStringToMapOfStringToNumber\":{\"firstKey\":{\"firstFirstKey\":1,\"firstSecondKey\":2},\"secondKey\":{\"secondSecondKey\":2,\"secondFirstKey\":1}}}"),
                Arguments.of(new FooObject(), "{\"stringField\":\"stringFieldValue\",\"byteField\":127,\"shortField\":32767,\"charField\":\"c\",\"intField\":2147483647,\"longField\":9223372036854775807,\"floatField\":3.4E38,\"doubleField\":1.7E308,\"booleanField\":true,\"byteFieldBoxed\":127,\"shortFieldBoxed\":32767,\"charFieldBoxed\":\"c\",\"intFieldBoxed\":2147483647,\"longFieldBoxed\":9223372036854775807,\"floatFieldBoxed\":3.4E38,\"doubleFieldBoxed\":1.7E308,\"booleanFieldBoxed\":true,\"listOfNumbers\":[1,2,3,4],\"mapOfStringToString\":{\"firstKey\":\"firstValue\",\"secondKey\":\"secondValue\"}}"),
                Arguments.of(new ClassWithListOfSimpleObjects(), "{\"listOfSimpleObjects\":[{\"field\":\"name0\"},{\"field\":\"name1\"},{\"field\":\"name2\"},{\"field\":\"name3\"},{\"field\":\"name4\"},{\"field\":\"name5\"},{\"field\":\"name6\"},{\"field\":\"name7\"},{\"field\":\"name8\"},{\"field\":\"name9\"}]}")
        );
    }

    @BeforeEach
    void setUpTest() {
        serializer = new JsonSerializer();
        gsonSerializer = new Gson();
    }

    @ParameterizedTest
    @MethodSource("provideObjectsWithFieldWithDifferentAccessModificators")
    void serializeSimple(Object obj, String expectedResult) throws IllegalAccessException {
        String actualResult = serializer.serialize(obj);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("provideObjectsWithFieldWithDifferentAccessModificators")
    void serializeWithGSON(Object obj, String expectedResult) {
        String actualResult = gsonSerializer.toJson(obj);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void fooTest() {
        String serializedJson = JsonSerializer.serialize(new ST_withNullField());
        String expectedJson = "{\"stringFieldNotNull\":\"stringFieldValue\",\"stringFieldNotNull2\":\"stringFieldValue\"}";
        assertEquals(expectedJson, serializedJson);
    }


    @Test
    void fooTest1() throws IllegalAccessException {
        ST_withNullField tmp = new ST_withNullField();
        List<Field> fields = Arrays.asList(tmp.getClass().getDeclaredFields());
        Field field = fields.get(0);
        Object str = field.get(tmp);
//        field.getInt()

    }
}


class ST_withNullField{

    String stringFieldNotNull = "stringFieldValue";
    String stringFieldNull = null;
    String stringFieldNotNull2 = "stringFieldValue";
//    int intField = 1;

}
