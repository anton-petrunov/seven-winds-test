package com.github.anton_petrunov.seven_winds_test.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserTo {
    private Integer id;
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
}
