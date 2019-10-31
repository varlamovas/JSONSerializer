package com.varlamovas.jsonserializer;

import java.lang.reflect.Array;

class ArrayWrapper {

    private final Object array;
    private final Class componentType;
    private final Class arrayClass;
    private final int arrayLength;

    ArrayWrapper(final Object array) {
        this.array = array;
        this.arrayClass = array.getClass();
        this.componentType = arrayClass.getComponentType();
        this.arrayLength = Array.getLength(array);
    }

    String getAsString(final int index) {
        if (componentType.equals(byte.class)) {
            byte b = Array.getByte(array, index);
            return String.valueOf(b);

        }
        if (componentType.equals(char.class)) {
            char c = Array.getChar(array, index);
            return String.valueOf(c);

        }
        if (componentType.equals(short.class)) {
            short s = Array.getShort(array, index);
            return String.valueOf(s);

        }
        if (componentType.equals(int.class)) {
            int i = Array.getInt(array, index);
            return String.valueOf(i);

        }
        if (componentType.equals(long.class)) {
            long l = Array.getLong(array, index);
            return String.valueOf(l);

        }
        if (componentType.equals(float.class)) {
            float f = Array.getFloat(array, index);
            return String.valueOf(f);

        }
        if (componentType.equals(double.class)) {
            double d = Array.getDouble(array, index);
            return String.valueOf(d);

        }
        if (componentType.equals(boolean.class)) {
            boolean bool = Array.getBoolean(array, index);
            return String.valueOf(bool);

        }
        return null;
    }
}
