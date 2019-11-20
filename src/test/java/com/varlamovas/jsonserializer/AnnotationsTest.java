package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.annotations.CustomAdapter;
import com.varlamovas.jsonserializer.annotations.CustomName;
import com.varlamovas.jsonserializer.testobjects.LocalDateAdapter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationsTest {

    @Test
    void annotationsTest__CustomName__serialize() {
        JsonSerializer jsonSerializer = new JsonSerializer();
        ClassWithCustomName instance = ClassWithCustomName.getInstance();

        String json = jsonSerializer.serialize(instance);
        assertEquals(ClassWithCustomName.getJson(), json);
    }

    @Test
    void annotationTest__CustomName__deserialize() {
        JsonSerializer jsonSerializer = new JsonSerializer();
        ClassWithCustomName instance = ClassWithCustomName.getInstance();
        String json = jsonSerializer.serialize(instance);

        ClassWithCustomName deserialized = jsonSerializer.deserialize(json, ClassWithCustomName.class);
        assertEquals(instance, deserialized);

    }


    @Test
    void test__CustomAdapter__serialize() {
        JsonSerializer serializer = new JsonSerializer();
        ClassWithDateField instance = ClassWithDateField.getInstance();
        String json = serializer.serialize(instance);

        assertEquals(ClassWithDateField.getJson(), json);
    }

    @Test
    void test__CustomAdapter__deserialize() {
        JsonSerializer serializer = new JsonSerializer();
        ClassWithDateField instance = ClassWithDateField.getInstance();
        String json = serializer.serialize(instance);
        ClassWithDateField deserialized = serializer.deserialize(json, ClassWithDateField.class);

        assertEquals(instance, deserialized);
    }
}

class ClassWithDateField {

    @CustomAdapter(adapterClass = LocalDateAdapter.class)
    LocalDate date;

    public static ClassWithDateField getInstance() {
        ClassWithDateField instance = new ClassWithDateField();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"date\":\"15.08.94\"}";
    }

    private void setFields() {
        date = LocalDate.of(1994, 8, 15);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;
        ClassWithDateField that = (ClassWithDateField) obj;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}

class ClassWithCustomName {

    @CustomName(name = "bla")
    String datefield;

    public static ClassWithCustomName getInstance() {
        ClassWithCustomName instance = new ClassWithCustomName();
        instance.setFields();
        return instance;
    }

    public static String getJson() {
        return "{\"bla\":\"fooFoo\"}";
    }

    private void setFields() {
        datefield = "fooFoo";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassWithCustomName that = (ClassWithCustomName) o;
        return Objects.equals(datefield, that.datefield);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datefield);
    }
}