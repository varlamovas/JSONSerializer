package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Array;

public class ArrayWrapper {

    private final Object array;
    private final Class componentType;
    private final Class arrayClass;
    private final int arrayLength;

    public ArrayWrapper(final Object array) {
        this.array = array;
        this.arrayClass = array.getClass();
        this.componentType = arrayClass.getComponentType();
        this.arrayLength = Array.getLength(array);
    }

    public String getAsString(final int index) {
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

    public void setValue(Object obj, int index) {
        Array.set(array, index, obj);
    }

    public void setValue(Token token, int index) {
        if (componentType.equals(byte.class)) {
            byte b = Byte.parseByte(token.getValue());
            Array.setByte(array, index, b);
            return;

        }
        if (componentType.equals(char.class)) {
            char c = token.getValue().charAt(0);
            Array.setChar(array, index, c);
            return;

        }
        if (componentType.equals(short.class)) {
            short s = Short.parseShort(token.getValue());
            Array.setShort(array, index, s);
            return;

        }
        if (componentType.equals(int.class)) {
            int i = Integer.parseInt(token.getValue());
            Array.setInt(array, index, i);
            return;

        }
        if (componentType.equals(long.class)) {
            long l = Long.parseLong(token.getValue());
            Array.setLong(array, index, l);
            return;

        }
        if (componentType.equals(float.class)) {
            float f = Float.parseFloat(token.getValue());
            Array.setFloat(array, index, f);
            return;

        }
        if (componentType.equals(double.class)) {
            double d = Double.parseDouble(token.getValue());
            Array.setDouble(array, index, d);
            return;

        }
        if (componentType.equals(boolean.class)) {
            boolean bool = Boolean.parseBoolean(token.getValue());
            Array.setBoolean(array, index, bool);
            return;

        }
    }
}
