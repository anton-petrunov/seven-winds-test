package com.github.antonpetrunov.sevenwindstest.service;

public class EntityNotNewException extends RuntimeException {
    public EntityNotNewException(Class clazz, Integer id) {
        super(String.format("%s id=%d must be new (id=null)", clazz.getSimpleName(), id));
    }
}
