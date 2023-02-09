package com.github.antonpetrunov.sevenwindstest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserTo {
    private Integer id;
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
}
