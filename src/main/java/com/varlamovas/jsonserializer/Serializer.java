package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.ObjectAdapter;
import com.varlamovas.jsonserializer.adapters.PrimitiveAdapter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Serializer {

    private Object obj;
    private StringBuilder jsonBuilder;
    private boolean expectColon = false;

    public Serializer(Object obj) {
        this.obj = obj;
        this.jsonBuilder = new StringBuilder();
    }

    public String serialize() {

        parseObject(obj);
        return jsonBuilder.toString();
    }


    public void parseObject(Object obj) {
        withCirclyBrackets(() -> {
            List<Field> allFields = FieldRetriever.getAllFields(obj);
            processDeclaredFields(allFields, obj);
        });
    }

    public void processDeclaredFields(List<Field> declaredFields, Object obj) {
        expectColon = false;
        for (Field field : declaredFields) {
            tryColon();
            parsePropertyValue(field, obj);
        }
    }

    public void parsePropertyValue(Field field, Object instanceWithField) {

        if (field.getDeclaringClass().isPrimitive()) {
            jsonBuilder.append("\"");
            jsonBuilder.append(field.getName());
            jsonBuilder.append("\"");
            jsonBuilder.append(":");
            parsePrimitiveItem(field, instanceWithField);
            return;
        }

        Object fieldObject = FieldRetriever.getFieldObject(field, instanceWithField);
        if (fieldObject == null) {
            expectColon = false;
            return;
        }

        jsonBuilder.append("\"");
        jsonBuilder.append(field.getName());
        jsonBuilder.append("\"");
        jsonBuilder.append(":");
        parseItem(fieldObject);

    }

    public void parsePrimitiveItem(Field field, Object instanceWithField) {
        PrimitiveAdapter adapter = AdapterFactory.getPrimitiveAdapter();
        jsonBuilder.append(adapter.toJson(field, instanceWithField));

    }

    public void parseItem(Object item) {

        Class clazz = item.getClass();
        ObjectAdapter adapter = AdapterFactory.getAdapter(item);
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

    private void parseArrayType(Object arrayTypeObject) {
        withSquareBrackets(() -> {
            Class componentType = arrayTypeObject.getClass().getComponentType();
            int arrayLength = Array.getLength(arrayTypeObject);
            ArrayUtils arrayUtils = new ArrayUtils(arrayTypeObject);
            boolean isPrimitive = componentType.isPrimitive();

            for (int i = 0; i < arrayLength; i++) {
                tryColon();

                if (isPrimitive) {
                    jsonBuilder.append(arrayUtils.getAsString(i));
                } else {
                    Object item = Array.get(arrayTypeObject, i);
                    parseItem(item);
                }
            }
        });
    }


    public void parseMapType(Object mapTypeObject) {
        withCirclyBrackets(() -> {
            Map<Object, Object> map = (Map<Object, Object>) mapTypeObject;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                tryColon();
                jsonBuilder.append(StringUtils.wrapByQuotes(entry.getKey().toString()));
                jsonBuilder.append(":");
                parseItem(entry.getValue());
            }
        });
    }

    public void parseCollectionType(Object collectionTypeObject) {
        withSquareBrackets(() -> {
            Collection<Object> collection = (Collection<Object>) collectionTypeObject;
            for (Object collectionInnerObject : collection) {
                tryColon();
                parseItem(collectionInnerObject);
            }
        });
    }

    void withSquareBrackets(Runnable runnable) {
        expectColon = false;
        jsonBuilder.append("[");
        runnable.run();
        jsonBuilder.append("]");
    }

    void withCirclyBrackets(Runnable runnable) {
        expectColon = false;
        jsonBuilder.append("{");
        runnable.run();
        jsonBuilder.append("}");
    }

    void tryColon() {
        if (expectColon) jsonBuilder.append(",");
        expectColon = true;
    }
}
