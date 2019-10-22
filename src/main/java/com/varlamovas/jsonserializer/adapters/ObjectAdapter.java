package com.varlamovas.jsonserializer.adapters;

import com.varlamovas.jsonserializer.FieldRetriever;
import com.varlamovas.jsonserializer.tokens.Token;

import java.lang.reflect.Field;

public interface ObjectAdapter extends BaseAdapter {
//    <T> T fromJson(Token token);
    String toJson(Object value);

}
