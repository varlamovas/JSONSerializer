package com.varlamovas.jsonserializer.testobjects;

import java.util.Objects;

public class SimpleObject implements BaseObject {
    private String field;

    private void setField(String field) {
        this.field = field;
    }

    public static SimpleObject getInstance(String field) {
        SimpleObject instance = new SimpleObject();
        instance.setField(field);
        return instance;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleObject that = (SimpleObject) o;
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}
