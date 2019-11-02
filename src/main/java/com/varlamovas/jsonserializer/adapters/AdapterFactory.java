package com.varlamovas.jsonserializer.adapters;

public class AdapterFactory {

    private static final ObjectAdapter STRING_ADAPTER = new StringAdapter();
    private static final ObjectAdapter NUMBER_ADAPTER = new NumberAdapter();
    private static final ObjectAdapter CHARACTER_ADAPTER = new CharacterAdapter();
    private static final ObjectAdapter BOOLEAN_ADAPTER = new BooleanTypeAdapter();
    private static final ObjectAdapter NULL_ADAPTER = new NullAdapter();
    private static final PrimitiveAdapter PRIMITIVE_ADAPTER = new PrimitiveAdapter();
    private static final ByteAdapter BYTE_ADAPTER = new ByteAdapter();
    private static final ShortAdapter SHORT_ADAPTER = new ShortAdapter();
    private static final IntegerAdapter INTEGER_ADAPTER = new IntegerAdapter();
    private static final LongAdapter LONG_ADAPTER = new LongAdapter();
    private static final FloatAdapter FLOAT_ADAPTER = new FloatAdapter();
    private static final DoubleAdapter DOUBLE_ADAPTER = new DoubleAdapter();

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
        }
        else if (clazz.equals(Byte.class)) {
            return BYTE_ADAPTER;
        }
        else if (clazz.equals(Short.class)) {
            return SHORT_ADAPTER;
        }
        else if (clazz.equals(Integer.class)) {
            return INTEGER_ADAPTER;
        }
        else if (clazz.equals(Long.class)) {
            return LONG_ADAPTER;
        }
        else if (clazz.equals(Double.class)) {
            return DOUBLE_ADAPTER;
        }
        else if (clazz.equals(Float.class)) {
            return FLOAT_ADAPTER;
        }
        else if (Number.class.isAssignableFrom(clazz)) {
            return NUMBER_ADAPTER;
        }

        return null;
    }

    public static PrimitiveAdapter getPrimitiveAdapter() {
        return PRIMITIVE_ADAPTER;
    }
}
