package com.github.anton_petrunov.seven_winds_test.exceptions;

public class EntityNotNewException extends RuntimeException {
    public EntityNotNewException(Class clazz, Integer id) {
        super(String.format("%s id=%d must be new (id=null)", clazz.getSimpleName(), id));
    }
}
