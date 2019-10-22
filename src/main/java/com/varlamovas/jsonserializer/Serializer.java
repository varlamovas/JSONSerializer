package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.adapters.AdapterFactory;
import com.varlamovas.jsonserializer.adapters.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Serializer {

    private Object obj;
    private StringBuilder jsonBuilder;

    public Serializer(Object obj) {
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
//        jsonBuilder.append("\"");
//        jsonBuilder.append(field.getName());
//        jsonBuilder.append("\"");
//        jsonBuilder.append(":");
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
        if (Collection.class.isAssignableFrom(clazz)) {
            parseCollectionType(item);
            return;
        }
        if (Map.class.isAssignableFrom(clazz)) {
            parseMapType(item);
            return;
        }
        BaseAdapter adapter = AdapterFactory.getAdapter(item, clazz);
        if (adapter != null) {
            jsonBuilder.append(adapter.toJson(item));
        } else {
            parseObject(item);
        }

    }

    private String parseArrayType(Object arrayTypeObject) {

//        Stream arrayStream = Arrays.stream()
        return "";
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
        for (Object collectionInnerObject : collection) {
            if (expectColon) jsonBuilder.append(",");
            parseItem(collectionInnerObject);
            expectColon = true;
        }
        jsonBuilder.append("]");
    }

    void parseStringType(Object stringTypeObject) {
        assert stringTypeObject instanceof String;
        jsonBuilder.append("\"" + stringTypeObject.toString() + "\"");
    }

}
