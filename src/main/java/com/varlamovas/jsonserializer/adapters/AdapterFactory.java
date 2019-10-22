package com.varlamovas.jsonserializer.adapters;

import java.lang.reflect.Field;

public class AdapterFactory {
    public static BaseAdapter getAdapter(Object object, Class<?> clazz) {
        if (object instanceof String) {
//            parseStringType(object);
            return new StringAdapter();
        } else if (clazz.isPrimitive() || object instanceof Number) {
//            parsePrimitiveType(object);
            return new PrimitiveTypeAdapter();
        }
        else if (object instanceof Character) {
//            parseCharacter(object);
            return new CharacterAdapter();
        } else if (object instanceof Boolean) {
//            parseBooleanType(object);
            return new BooleanTypeAdapter();
        } else if (object == null){
            return new NullAdapter();
        }
        else return null;
    }
}
