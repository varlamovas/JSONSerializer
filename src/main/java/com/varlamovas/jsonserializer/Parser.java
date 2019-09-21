package com.varlamovas.jsonserializer;

import com.varlamovas.jsonserializer.tokens.Token;
import com.varlamovas.jsonserializer.tokens.TokenInterface;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Parser<T> {

    private Lexer lexer;
    private List<Field> listOfFields;
    private Seed<T> seed;

    Parser(Reader reader, Seed<T> seed) {
        lexer = new Lexer((StringReader) reader);
        this.seed = seed;
        this.listOfFields = seed.getAllFields();
    }

    public T parseCommaSeparated() {

        assert Token.LEFT_CURLY_BRACE == lexer.nextToken();
        T instance = seed.newInstance();
        while (true) {
            TokenInterface<?> propertyToken = lexer.nextToken();
            if (propertyToken == Token.RIGHT_CURLY_BRACE) break;
            assert Token.COLON == lexer.nextToken();
            assert listOfFields.stream().anyMatch(f -> f.getName().equals(propertyToken.getValue()));
            TokenInterface<?> valueToken = lexer.nextToken();

            List<Field> fields = seed.getAllFields().stream().filter(f -> f.getName().equals(propertyToken.getValue())).collect(Collectors.toList());
            assert !fields.isEmpty();
            Field field = fields.get(0);

            //  deserialized string field
            if (field.getType().equals(String.class)){
                try {
                    field.set(instance, valueToken.getValue());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //  deserialized <? extends Number> field
            else if (Number.class.isAssignableFrom(field.getType())){
                try {
                    field.set(instance, field.getType().cast(valueToken.getValue()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            // Character type
            else if (field.getType().equals(Character.class)) {
                try {
                    field.set(instance, ((String) valueToken.getValue()).charAt(0));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            // Boolean type
            else if (field.getType().equals(Boolean.class)) {
                try {
                    field.set(instance, valueToken.getValue());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //collection
            else if (Collection.class.isAssignableFrom(field.getType()) && valueToken.equals(Token.LEFT_SQUARE_BRACKET)) {

                Collection<?> collection = null;
                while (true) {
//                    TokenInterface<?> token = lexer.nextToken();
                    Type type = field.getGenericType();
                    ParameterizedType pType = null;
                    Method add = null;
                    Constructor<?> constructor = null;
                    if (pType == null && type instanceof ParameterizedType) {
                        pType = (ParameterizedType) type;
                        try {
                            collection = (Collection) field.getType().newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        try {
                            add = field.getType().getMethod("add", Object.class);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                    while (true) {
                        TokenInterface<?> tk = lexer.nextToken();
                        if (tk.equals(Token.COMMA)) continue;
                        if (tk.equals(Token.RIGHT_SQUARE_BRACKET)) break;
//                        if ()
                        try {
                            add.invoke(collection, tk.getValue());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    };
                    break;
                }
                try {
                    field.set(instance, collection);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            TokenInterface<?> next = lexer.nextToken();
            assert next.equals(Token.COMMA) || next.equals(Token.RIGHT_CURLY_BRACE);
            if (next.equals(Token.RIGHT_CURLY_BRACE)) break;
        }
        return instance;
    }
}
