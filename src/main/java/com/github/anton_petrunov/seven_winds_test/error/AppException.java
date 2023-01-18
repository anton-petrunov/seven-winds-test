package com.github.anton_petrunov.seven_winds_test.error;

import lombok.Getter;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//TODO: переименовать пакет с error на exceptions
//TODO: переименовать класс исключения AppException на что-то более конкретное
//TODO: убрать геттер
//TODO: сделать шаблон в виде строки (private поле), который будет являтся сообщением будущего объекта-исключения
//TODO: правильнее будет отнаследоваться от RuntimeException
@Getter
public class AppException extends ResponseStatusException {
    private final ErrorAttributeOptions options;

    public AppException(HttpStatus status, String message, ErrorAttributeOptions options) {
        super(status, message);
        this.options = options;
    }
}
