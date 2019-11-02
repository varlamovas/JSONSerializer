package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.exceptions.FieldRetrieveException;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class PrimitiveAdapter implements BaseAdapter {

    public void fromJson(Field field, Object objectWithField, Token token) {
        Class clazz = field.getType();
        try {
            if (clazz.equals(byte.class)) {
                String valueAsString = token.getValue();
                byte value = Byte.parseByte(valueAsString);
                field.setByte(objectWithField, value);
                return;
            }
            if (clazz.equals(char.class)) {
                String valueAsString = token.getValue();
                char value = valueAsString.charAt(0);
                field.setChar(objectWithField, value);
                return;
            }
            if (clazz.equals(short.class)) {
                String valueAsString = token.getValue();
                short value = Short.parseShort(valueAsString);
                field.setShort(objectWithField, value);
                return;
            }
            if (clazz.equals(int.class)) {
                String valueAsString = token.getValue();
                int value = Integer.parseInt(valueAsString);
                field.setInt(objectWithField, value);
                return;
            }
            if (clazz.equals(long.class)) {
                String valueAsString = token.getValue();
                long value = Long.parseLong(valueAsString);
                field.setLong(objectWithField, value);
                return;
            }
            if (clazz.equals(double.class)) {
                String valueAsString = token.getValue();
                double value = Double.parseDouble(valueAsString);
                field.setDouble(objectWithField, value);
                return;
            }

            if (clazz.equals(float.class)) {
                String valueAsString = token.getValue();
                float value = Float.parseFloat(valueAsString);
                field.setFloat(objectWithField, value);
                return;
            }
            if (clazz.equals(boolean.class)) {
                String valueAsString = token.getValue();
                boolean value = Boolean.parseBoolean(valueAsString);
                field.setBoolean(objectWithField, value);
                return;
            }
        } catch (IllegalAccessException ignored) {

        }
    }

    public String toJson(Field field, Object objectWithField) {
        Class clazz = field.getDeclaringClass();
        try {
            if (clazz.equals(byte.class)) {
                byte b = field.getByte(objectWithField);
                return String.valueOf(b);
            }
            if (clazz.equals(char.class)) {
                char c = field.getChar(objectWithField);
                return String.valueOf(c);
            }
            if (clazz.equals(short.class)) {
                short s = field.getShort(objectWithField);
                return String.valueOf(s);
            }
            if (clazz.equals(int.class)) {
                int i = field.getInt(objectWithField);
                return String.valueOf(i);
            }
            if (clazz.equals(long.class)) {
                long l = field.getLong(objectWithField);
                return String.valueOf(l);
            }
            if (clazz.equals(float.class)) {
                float f = field.getFloat(objectWithField);
                return String.valueOf(f);
            }
            if (clazz.equals(double.class)) {
                double d = field.getDouble(objectWithField);
                return String.valueOf(d);
            }
            if (clazz.equals(boolean.class)) {
                boolean bool = field.getBoolean(objectWithField);
                return String.valueOf(bool);
            }
        } catch (IllegalAccessException e) {
            //TODO : need to clarify
            throw new FieldRetrieveException();
        }
        throw new FieldRetrieveException();
    }
}
