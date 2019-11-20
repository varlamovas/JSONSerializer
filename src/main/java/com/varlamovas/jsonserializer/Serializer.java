package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.adapters.*;
import com.varlamovas.jsonserializer.annotations.CustomAdapter;
import com.varlamovas.jsonserializer.annotations.CustomName;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

class Serializer {

    private final static String LEFT_CURLY_BRACE = "{";
    private final static String RIGHT_CURLY_BRACE = "}";
    private final static String LEFT_SQUARE_BRACKET = "[";
    private final static String RIGHT_SQUARE_BRACKET = "]";
    private final static String COLON = ":";
    private final static String COMMA = ",";
    private final static String QUOTE = "\"";

    private final Object obj;
    private final StringBuilder jsonBuilder;

    private boolean expectColon = false;

    Serializer(final Object obj) {
        this.obj = obj;
        this.jsonBuilder = new StringBuilder();
    }


    /**
     * @return JSON representation of the obj passed in the constructor
     */
    String serialize() {
        parseObject(obj);
        return jsonBuilder.toString();
    }


    private void parseObject(final Object obj) {
        withCirclyBrackets(() -> {
            List<Field> allFields = FieldRetriever.getAllFields(obj);
            processDeclaredFields(allFields, obj);
        });
    }

    private void processDeclaredFields(final List<Field> declaredFields,final Object obj) {
        expectColon = false;
        for (Field field : declaredFields) {
            tryComma();
            parsePropertyValue(field, obj);
        }
    }

    private void parsePropertyValue(final Field field, final Object instanceWithField) {

        if (field.getDeclaringClass().isPrimitive()) {
//            withQuotes(field::getName);
            withQuotes(() -> getFieldName(field));
            jsonBuilder.append(Serializer.COLON);
            parsePrimitiveItem(field, instanceWithField);
            return;
        }

        Object fieldObject = FieldRetriever.getFieldObject(field, instanceWithField);
        if (fieldObject == null) {
            expectColon = false;
            return;
        }

//        withQuotes(field::getName);
        withQuotes(() -> getFieldName(field));
        jsonBuilder.append(Serializer.COLON);

        if (field.isAnnotationPresent(CustomAdapter.class)) {
            CustomAdapter annotation = field.getAnnotation(CustomAdapter.class);
            SimpleObjectAdapter adapter = null;
            try {
                adapter = annotation.adapterClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            assert adapter != null;
            parseItemByCustomAdapter(fieldObject, adapter);
            return;
        }

        parseItem(fieldObject);

    }

    private void parsePrimitiveItem(final Field field, final Object instanceWithField) {
        PrimitiveAdapter adapter = AdapterFactory.getPrimitiveAdapter();
        jsonBuilder.append(adapter.toJson(field, instanceWithField));

    }

    private void parseItemByCustomAdapter(Object item, SimpleObjectAdapter adapter) {
        withQuotes(() -> adapter.toJson(item));
    }

    private void parseItem(final Object item) {

        Class clazz = item.getClass();
        ObjectAdapter adapter;
        adapter = AdapterFactory.getAdapter(item);

        if (adapter != null) {
            jsonBuilder.append(adapter.toJson(item));
            return;
        }
        if (Collection.class.isAssignableFrom(clazz)) {
            parseCollectionType(item);
            return;
        }
        if (Map.class.isAssignableFrom(clazz)) {
            parseMapType(item);
            return;
        }
        if (clazz.isArray()) {
            parseArrayType(item);
            return;
        }
        parseObject(item);

    }

    private void parseArrayType(final Object arrayTypeObject) {
        withSquareBrackets(() -> {
            Class componentType = arrayTypeObject.getClass().getComponentType();
            int arrayLength = Array.getLength(arrayTypeObject);
            ArrayWrapper arrayWrapper = new ArrayWrapper(arrayTypeObject);
            boolean isPrimitive = componentType.isPrimitive();

            for (int i = 0; i < arrayLength; i++) {
                tryComma();

                if (isPrimitive) {
                    jsonBuilder.append(arrayWrapper.getAsString(i));
                } else {
                    Object item = Array.get(arrayTypeObject, i);
                    parseItem(item);
                }
            }
        });
    }

    private void parseMapType(final Object mapTypeObject) {
        withCirclyBrackets(() -> {
            @SuppressWarnings("unchecked") Map<Object, Object> map = (Map<Object, Object>) mapTypeObject;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                tryComma();
                withQuotes(entry.getKey()::toString);
                jsonBuilder.append(Serializer.COLON);
                parseItem(entry.getValue());
            }
        });
    }

    private void parseCollectionType(final Object collectionTypeObject) {
        withSquareBrackets(() -> {
            Collection<?> collection = (Collection<?>) collectionTypeObject;
            for (Object collectionInnerObject : collection) {
                tryComma();
                parseItem(collectionInnerObject);
            }
        });
    }

    private void withSquareBrackets(final Runnable runnable) {
        expectColon = false;
        jsonBuilder.append(Serializer.LEFT_SQUARE_BRACKET);
        runnable.run();
        jsonBuilder.append(Serializer.RIGHT_SQUARE_BRACKET);
    }

    private void withCirclyBrackets(final Runnable runnable) {
        expectColon = false;
        jsonBuilder.append(Serializer.LEFT_CURLY_BRACE);
        runnable.run();
        jsonBuilder.append(Serializer.RIGHT_CURLY_BRACE);
    }

    private void withQuotes(final Supplier<String> supplier) {
        jsonBuilder.append(Serializer.QUOTE);
        jsonBuilder.append(supplier.get());
        jsonBuilder.append(Serializer.QUOTE);
    }

    private void tryComma() {
        if (expectColon) jsonBuilder.append(Serializer.COMMA);
        expectColon = true;
    }

    private String getFieldName(Field field) {
        String name;
        if (field.isAnnotationPresent(CustomName.class)) {
            CustomName annotation = field.getAnnotation(CustomName.class);
            name = annotation.name();
        } else {
            name = field.getName();
        }
        return name;
    }
}
