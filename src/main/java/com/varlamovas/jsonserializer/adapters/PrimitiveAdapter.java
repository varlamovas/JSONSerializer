package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.exceptions.FieldRetrieveException;

import java.lang.reflect.Field;

public class PrimitiveAdapter implements BaseAdapter {

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

    public BaseAdapter getPrimitiveAdapterByClass(Class<?> clazz) {
            if (clazz.equals(byte.class)) {
                return new ByteAdapter();

            }
            if (clazz.equals(char.class)) {
                return new CharAdapter();

            }
            if (clazz.equals(short.class)) {
                return new ShortAdapter();

            }
            if (clazz.equals(int.class)) {
                return new IntAdapter();

            }
            if (clazz.equals(long.class)) {
                return new LongAdapter();

            }
            if (clazz.equals(float.class)) {
                return new FloatAdapter();

            }
            if (clazz.equals(double.class)) {
                return new DoubleAdapter();

            }
            if (clazz.equals(boolean.class)) {
                return new BoolAdapter();

            }
        return null;
    }

    interface PrimTypeAdapt {}

    class ByteAdapter implements BaseAdapter {
        String toJson(byte b) {return String.valueOf(b);}
    }
    class CharAdapter implements BaseAdapter {
        String toJson(char c) {return String.valueOf(c);}
    }
    class ShortAdapter implements BaseAdapter {
        String toJson(short s) {return String.valueOf(s);}
    }
    class IntAdapter implements BaseAdapter {
        String toJson(int i) {return String.valueOf(i);}
    }
    class LongAdapter implements BaseAdapter {
        String toJson(long l) {return String.valueOf(l);}
    }
    class FloatAdapter implements BaseAdapter {
        String toJson(float f) {return String.valueOf(f);}
    }
    class DoubleAdapter implements BaseAdapter {
        String toJson(double d) {return String.valueOf(d);}
    }
    class BoolAdapter implements BaseAdapter {
        String toJson(boolean bool) {return String.valueOf(bool);}
    }

}
