package com.varlamovas.jsonserializer.adapters;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AdapterFactory {

    private static final BaseAdapter STRING_ADAPTER = new StringAdapter();


    public static BaseAdapter getAdapter(Object object, Class<?> clazz) {
        if (object instanceof String) {
//            parseStringType(object);
            return STRING_ADAPTER;
        } else if (clazz.isPrimitive() || object instanceof Number) {
//            parsePrimitiveType(object);
            return new PrimitiveTypeAdapter();
        } else if (object instanceof Character) {
//            parseCharacter(object);
            return new CharacterAdapter();
        } else if (object instanceof Boolean) {
//            parseBooleanType(object);
            return new BooleanTypeAdapter();
        } else if (object == null) {
            return new NullAdapter();
        } else return null;
    }
}
