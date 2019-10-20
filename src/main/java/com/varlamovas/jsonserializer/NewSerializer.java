package com.varlamovas.jsonserializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class NewSerializer {

    private Object obj;
    private StringBuilder jsonBuilder;

    public NewSerializer(Object obj) {
        this.obj = obj;
        this.jsonBuilder = new StringBuilder();
    }

    public String serialize() {

        parseObject(obj);

        return jsonBuilder.toString();
    }


    public void parseObject(Object obj) {
        jsonBuilder.append("{");

        List<Field> allFields = FieldRetriever.getAllFields(obj);
        processDeclaredFields(allFields, obj);

        jsonBuilder.append("}");
    }

    public void processDeclaredFields(List<Field> declaredFields, Object obj) {
        boolean parsedField = false;
        boolean expectColon = false;
        for (Field field : declaredFields) {
            if (parsedField) {
                if (expectColon) jsonBuilder.append(",");
            }
            parsedField = parseField(field, obj);
            expectColon = true;
        }
    }

    public boolean parseField(Field field, Object obj) {
        jsonBuilder.append("\"");
        jsonBuilder.append(field.getName());
        jsonBuilder.append("\"");
        jsonBuilder.append(":");
        Object fieldObject = null;
        try {
            fieldObject = field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (fieldObject != null) {
            parseItem(fieldObject);
            return true;
        } else {
            return false;
        }
    }

    public void parseItem(Object item) {
        Class clazz = item.getClass();
        if (item instanceof String) {
            parseStringType(item);
        } else if (clazz.isPrimitive() || item instanceof Number) {
            parsePrimitiveType(item);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            parseCollectionType(item);
        } else if (Map.class.isAssignableFrom(clazz)) {
            parseMapType(item);
        } else if (item instanceof Character) {
            parseCharacter(item);
        } else if (item instanceof Boolean) {
            parseBooleanType(item);
        } else {
            parseObject(item);
        }
    }

    private String parseArrayType(Object arrayTypeObject) {

//        Stream arrayStream = Arrays.stream()
        return "";
    }

    private void parseBooleanType(Object booleanTypeObject) {
        assert booleanTypeObject instanceof Boolean;
        jsonBuilder.append(booleanTypeObject.toString());
//        return booleanTypeObject.toString();
    }

    private void parseCharacter(Object characterTypeObject) {
        assert characterTypeObject instanceof Character;
        jsonBuilder.append("\"");
        jsonBuilder.append(characterTypeObject.toString());
        jsonBuilder.append("\"");
//        return "\"" + characterTypeObject.toString() + "\"";
    }

    public void parseMapType(Object mapTypeObject) {
        jsonBuilder.append("{");
        Map<Object, Object> map = (Map<Object, Object>) mapTypeObject;
        boolean expectColon = false;
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            if (expectColon) jsonBuilder.append(",");
            parseStringType(entry.getKey().toString());
            jsonBuilder.append(":");
            parseItem(entry.getValue());
            expectColon = true;
        }
        jsonBuilder.append("}");
    }

    public void parseCollectionType(Object collectionTypeObject) {
        jsonBuilder.append("[");
        boolean expectColon = false;
        Collection<Object> collection = (Collection<Object>) collectionTypeObject;
        List<String> collectionInnerObjectsAsListOfStrings = new ArrayList<>();
        for (Object collectionInnerObject : collection) {
            if (expectColon) jsonBuilder.append(",");
            parseItem(collectionInnerObject);
            expectColon = true;
        }
        jsonBuilder.append("]");
    }

    void parsePrimitiveType(Object primitiveTypeObject) {
        jsonBuilder.append(primitiveTypeObject.toString());
    }

    void parseStringType(Object stringTypeObject) {
        assert stringTypeObject instanceof String;
//        return "\"" + stringTypeObject.toString() + "\"";
        jsonBuilder.append("\"" + stringTypeObject.toString() + "\"");
    }

}
