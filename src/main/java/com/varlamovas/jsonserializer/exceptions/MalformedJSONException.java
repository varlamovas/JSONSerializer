package com.varlamovas.jsonserializer.exceptions;

public class MalformedJSONException extends RuntimeException {
    public MalformedJSONException(String message) {
        super(message);
    }
    public MalformedJSONException(String message, Throwable cause) { super(message, cause);}
}
