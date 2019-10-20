package com.varlamovas.jsonserializer;

import java.lang.reflect.Field;
import java.util.*;

public class Serializer {

    private Object obj;
    private StringBuilder jsonBuilder;

    public Serializer(Object obj) {
        this.obj = obj;
        this.jsonBuilder = new StringBuilder();
    }

    public String serialize() {

        jsonBuilder.append("{");

        List<Field> allFields = FieldRetriever.getAllFields(obj);
        String processedFields = processDeclaredFields(allFields, obj);

        jsonBuilder.append(processedFields);
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public String processDeclaredFields(List<Field> declaredFields, Object obj) {
        List<String> processedFields = new ArrayList<>();
        for (Field field : declaredFields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            String parsedField = parseField(field, obj);
            if (!parsedField.isEmpty()) {
                processedFields.add(parsedField);
            }
        }
        return String.join(",", processedFields);
    }

    public String parseField(Field field, Object obj) {
        StringBuilder fieldAsString = new StringBuilder();
        fieldAsString.append("\"");
        fieldAsString.append(field.getName());
        fieldAsString.append("\"");
        fieldAsString.append(":");
        Class clazz = field.getType();
        Object fieldObject = null;
        try {
            fieldObject = field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (fieldObject != null) {
            fieldAsString.append(parseItem(fieldObject));
            return fieldAsString.toString();
        } else {
            return "";
        }
    }

    public String parseItem(Object item) {
        StringBuilder itemAsString = new StringBuilder();
        Class clazz = item.getClass();
        if (item instanceof String) {
            itemAsString.append(parseStringType(item));
        } else if (clazz.isPrimitive() || item instanceof Number) {
            itemAsString.append(parsePrimitiveType(item));
        } else if (Collection.class.isAssignableFrom(clazz)) {
            itemAsString.append(parseCollectionType(item));
        } else if (Map.class.isAssignableFrom(clazz)) {
            itemAsString.append(parseMapType(item));
        } else if (item instanceof Character) {
            itemAsString.append( parseCharacter(item) );
        } else if (item instanceof Boolean) {
            itemAsString.append(parseBooleanType(item));
        } else {
            itemAsString.append(new Serializer(item).serialize());
        }
        return itemAsString.toString();
    }

    private String parseArrayType(Object arrayTypeObject) {

//        Stream arrayStream = Arrays.stream()
        return "";
    }

    private String parseBooleanType(Object booleanTypeObject) {
        assert booleanTypeObject instanceof Boolean;
        return booleanTypeObject.toString();
    }

    private String parseCharacter(Object characterTypeObject) {
        assert characterTypeObject instanceof Character;
        return "\"" + characterTypeObject.toString() + "\"";
    }

    public String parseMapType(Object mapTypeObject) {
        StringBuilder mapAsString = new StringBuilder();
        mapAsString.append("{");
        Map<Object, Object> map = (Map<Object, Object>) mapTypeObject;
        List<String> mapInnerObjectsAsListOfStrings = new ArrayList<>();
        String keyValueString;
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            keyValueString = parseStringType(entry.getKey().toString()) + ":" + parseItem(entry.getValue());
            mapInnerObjectsAsListOfStrings.add(keyValueString);
        }
        mapAsString.append(String.join(",", mapInnerObjectsAsListOfStrings))
                .append("}");
        return mapAsString.toString();
    }

    public String parseCollectionType(Object collectionTypeObject) {
        StringBuilder collectionAsString = new StringBuilder();
        collectionAsString.append("[");
        Collection<Object> collection = (Collection<Object>) collectionTypeObject;
        List<String> collectionInnerObjectsAsListOfStrings = new ArrayList<>();
        for (Object collectionInnerObject : collection) {
            collectionInnerObjectsAsListOfStrings.add(parseItem(collectionInnerObject));
        }
        collectionAsString.append(String.join(",", collectionInnerObjectsAsListOfStrings));
        collectionAsString.append("]");
        return collectionAsString.toString();
    }

    String parsePrimitiveType(Object primitiveTypeObject) {
        return primitiveTypeObject.toString();
    }

    String parseStringType(Object stringTypeObject) {
        assert stringTypeObject instanceof String;
        return "\"" + stringTypeObject.toString() + "\"";
    }

}
