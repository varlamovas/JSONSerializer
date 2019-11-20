package com.varlamovas.jsonserializer.annotations;

import com.varlamovas.jsonserializer.adapters.SimpleObjectAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAdapter {
    Class<? extends SimpleObjectAdapter> adapterClass();
}
