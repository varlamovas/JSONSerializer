package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.tokens.FloatToken;
import com.varlamovas.jsonserializer.tokens.IntegerToken;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public class NumberAdapter implements ObjectAdapter {
    @Override
    public void fromJson(Token token, Field field, Object instance) {
        if (field.getType().equals(Number.class)) {
            Number number = null;
            if (token instanceof IntegerToken) {
                number = new Long(token.getValue());
            } else if (token instanceof FloatToken) {
                number = new Double(token.getValue());
            }
            FieldRetriever.setFieldObject(field, instance, number);
        } else if (field.getType().equals(Byte.class)) {
            Byte number = new Byte(token.getValue());
            FieldRetriever.setFieldObject(field, instance, number);
        } else if (field.getType().equals(Short.class)) {
            Short number = new Short(token.getValue());
            FieldRetriever.setFieldObject(field, instance, number);
        } else if (field.getType().equals(Integer.class)) {
            Integer number = new Integer(token.getValue());
            FieldRetriever.setFieldObject(field, instance, number);
        } else if (field.getType().equals(Long.class)) {
            Long number = new Long(token.getValue());
            FieldRetriever.setFieldObject(field, instance, number);
        } else if (field.getType().equals(Float.class)) {
            Float number = new Float(token.getValue());
            FieldRetriever.setFieldObject(field, instance, number);
        } else {
            assert field.getType().equals(Double.class);
            Double number = new Double(token.getValue());
            FieldRetriever.setFieldObject(field, instance, number);
        }
    }

    @Override
    public String toJson(Object value) {
        return value.toString();
    }
}