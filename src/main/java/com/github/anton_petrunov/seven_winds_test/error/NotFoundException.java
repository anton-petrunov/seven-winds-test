package com.github.anton_petrunov.seven_winds_test.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
