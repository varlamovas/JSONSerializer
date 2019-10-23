package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;

public class AdapterFactory {

    private static final ObjectAdapter STRING_ADAPTER = new StringAdapter();
    private static final ObjectAdapter NUMBER_ADAPTER = new NumberAdapter();
    private static final ObjectAdapter CHARACTER_ADAPTER = new CharacterAdapter();
    private static final ObjectAdapter BOOLEAN_ADAPTER = new BooleanTypeAdapter();
    private static final ObjectAdapter NULL_ADAPTER = new NullAdapter();
    private static final PrimitiveAdapter PRIMITIVE_ADAPTER = new PrimitiveAdapter();

    public static ObjectAdapter getAdapter(Object object) {
        if (object instanceof String) {
            return STRING_ADAPTER;
        } else if (object instanceof Number) {
            return NUMBER_ADAPTER;
        } else if (object instanceof Character) {
            return CHARACTER_ADAPTER;
        } else if (object instanceof Boolean) {
            return BOOLEAN_ADAPTER;
        } else if (object == null) {
            return NULL_ADAPTER;}
        else return null;
    }

    public static ObjectAdapter getAdapter(Class clazz) {
        if (clazz.equals(String.class)) {
            return STRING_ADAPTER;
        } else if (clazz.equals(Character.class)) {
            return CHARACTER_ADAPTER;
        } else if (clazz.equals(Boolean.class)) {
            return BOOLEAN_ADAPTER;
        } else if (Number.class.isAssignableFrom(clazz)) {
            return NUMBER_ADAPTER;
        } else return null;
    }

    public static PrimitiveAdapter getPrimitiveAdapter() {
        return PRIMITIVE_ADAPTER;
    }
}
