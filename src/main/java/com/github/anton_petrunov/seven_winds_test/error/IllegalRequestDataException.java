package com.github.anton_petrunov.seven_winds_test.error;

public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(String message) {
        super(message);
    }
}
