package com.varlamovas.jsonserializer;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
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
}

class ClassWithArrayOfIntegersField {
    int[] arrayOfIntegersField = {1, 2, 3, 4};
}

class FooObject {
    String stringField = "stringFieldValue";
    String nullInsteadString = null;
    byte byteField = 127;
    short shortField = 32767;
    char charField = 'c';
    int intField = 2147483647;
    long longField = 9223372036854775807L;
    float floatField = 3.4e+38f;
    double doubleField = 1.7e+308;
    boolean booleanField = true;
    Byte byteFieldBoxed = 127;
    Short shortFieldBoxed = 32767;
    Character charFieldBoxed = 'c';
    Integer intFieldBoxed = 2147483647;
    Long longFieldBoxed = 9223372036854775807L;
    Float floatFieldBoxed = 3.4e+38f;
    Double doubleFieldBoxed = 1.7e+308;
    Boolean booleanFieldBoxed = true;
//    int[] array = {1, 2, 3, 4};
    List<Number> listOfNumbers = Arrays.asList(1, 2, 3, 4);
    Map<String, String> mapOfStringToString = new HashMap<String, String>() {{
        put("firstKey", "firstValue");
        put("secondKey", "secondValue");
    }};
}

class SimpleObject {
    String field;

    SimpleObject(String field) {
        this.field = field;
    }
}

class ClassWithListOfSimpleObjects {
    List<SimpleObject> listOfSimpleObjects = new ArrayList<>();

    ClassWithListOfSimpleObjects() {
        for (int i = 0; i < 10; ++i) {
            listOfSimpleObjects.add(new SimpleObject("name" + i));
        }
    }
}

class ClassWithMapOfStringToMapOfStringToNumber {
    Map<String, Map<String, Number>> mapOfStringToMapOfStringToNumber = new HashMap<String, Map<String, Number>>() {
        {
            put("firstKey", new HashMap<String, Number>() {{
                put("firstFirstKey", 1);
                put("firstSecondKey", 2);
            }});
            put("secondKey", new HashMap<String, Number>() {{
                put("secondFirstKey", 1);
                put("secondSecondKey", 2);
            }});
        }
    };
}

class ClassWithMapOfNumberToNumber {
    Map<Number, Number> mapOfStringToString = new HashMap<Number, Number>() {
        {
            put(1, 1);
            put(2, 2);
        }
    };
}

class ClassWithMapOfStringToString {
    Map<String, String> mapOfStringToString = new HashMap<String, String>() {
        {
            put("firstKey", "firstValue");
            put("secondKey", "secondValue");
        }
    };
}

class ClassWithQueueOfStringsField {
    Queue<String> queueOfStrings = new ArrayDeque<>(Arrays.asList("queue", "Of", "Strings"));
}

class ClassWithListOfListOfStringsField {
    List<List<String>> listOfListOfStrings = Arrays.asList(
            Arrays.asList("first", "list", "Of", "Strings"),
            Arrays.asList("second", "list", "Of", "Strings")
    );
}

class ClassWithSetOfStringsField {
    Set<String> setOfStrings = new HashSet<>(Arrays.asList("set", "Of", "Strings"));
}

class ClassWithArrayListOfStringField {
    ArrayList<String> listOfStrings;

    private void setListOfStrings() {
        listOfStrings = new ArrayList<>();
        listOfStrings.add("list");
        listOfStrings.add("Of");
        listOfStrings.add("Strings");
    }

    public static ClassWithArrayListOfStringField getInstance() {
        ClassWithArrayListOfStringField inst = new ClassWithArrayListOfStringField();
        inst.setListOfStrings();
        return inst;
    }

}

class ClassWithListOfStringField {
    List<String> listOfStrings;

    private void setListOfStrings() {
        listOfStrings = Arrays.asList("list", "Of", "Strings");
    }

    public static ClassWithListOfStringField getInstance() {
        ClassWithListOfStringField inst = new ClassWithListOfStringField();
        inst.setListOfStrings();
        return inst;
    }

}

class ParentClassWithSetter {
    String parentSettedString;

    public void setParentSettedString(String str) {
        parentSettedString = str;
    }
}

class ChildrenClassWithSetter extends ParentClassWithSetter {
    String childrenSettedString;

    public void setChildrenSettedString(String str) {
        childrenSettedString = str;
    }

    static ChildrenClassWithSetter getInstanceOfChildrenClassWithSetters() {
        ChildrenClassWithSetter childrenClassWithSetter = new ChildrenClassWithSetter();
        childrenClassWithSetter.setChildrenSettedString("childrenSettedStringValue");
        childrenClassWithSetter.setParentSettedString("parentSettedStringValue");
        return childrenClassWithSetter;
    }
}

class ClassWithSetter {
    String settedString;

    public void setSettedString(String str) {
        settedString = str;
    }

    static ClassWithSetter getInstanceOfClassWithSetters() {
        ClassWithSetter classWithSetter = new ClassWithSetter();
        classWithSetter.setSettedString("settedStringValue");
        return classWithSetter;
    }
}

class ClassOnlyWithPublicFields {
    public String stringField = "stringFieldValue";
    public int intField = 42;
}

class ClassOnlyWithPackagePrivateFields {
    String stringField = "stringFieldValue";
    int intField = 42;
}

class ClassOnlyWithProtectedFields {
    protected String stringField = "stringFieldValue";
    protected int intField = 42;
}

class ClassOnlyWithPrivateFields {
    private String stringField = "stringFieldValue";
    private int intField = 42;
}

class ClassOnlyWithFinalFields {
    final String stringField = "stringFieldValue";
    final int intField = 42;
}

class ClassOnlyWithStaticFields {
    static String stringField = "stringFieldValue";
    static int intField = 42;
}

class Parent {
    public String parentPublicString = "parentPublicStringValue";
    String parentPackagePrivateString = "parentPackagePrivateStringValue";
    protected String parentProtectedString = "parentProtectedStringValue";
    private String parentPrivateString = "parentPrivateStringValue";

    final public String finalParentPublicString = "finalParentPublicString";
    final String finalParentPackagePrivateString = "finalParentPackagePrivateString";
    final protected String finalParentProtectedString = "finalParentProtectedString";
    final private String finalParentPrivateString = "finalParentPrivateString";

    static public String staticParentPublicString = "staticParentPublicString";
    static String staticParentPackagePrivateString = "staticParentPackagePrivateString";
    static protected String staticParentProtectedString = "staticParentProtectedString";
    static private String staticParentPrivateString = "staticParentPrivateString";
}

class Children extends Parent {
    public String childrenPublicString = "childrenPublicStringValue";
    String childrenPackagePrivateString = "childrenPackagePrivateStringValue";
    protected String childrenProtectedString = "childrenProtectedStringValue";
    private String childrenPrivateString = "childrenPrivateStringValue";

    final public String finalChildrenPublicString = "finalChildrenPublicString";
    final String finalChildrenPackagePrivateString = "finalChildrenPackagePrivateString";
    final protected String finalChildrenProtectedString = "finalChildrenProtectedString";
    final private String finalChildrenPrivateString = "finalChildrenPrivateString";

    static public String staticChildrenPublicString = "staticChildrenPublicString";
    static String staticChildrenPackagePrivateString = "staticChildrenPackagePrivateString";
    static protected String staticChildrenProtectedString = "staticChildrenProtectedString";
    static private String staticChildrenPrivateString = "staticChildrenPrivateString";
}