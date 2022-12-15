package com.github.anton_petrunov.seven_winds_test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @Column(name = "email", nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 3, max = 128)
    private String email;

    @Column(name = "surname", nullable = false)
    @Size(min = 3, max = 128)
    @NotNull
    @NotBlank
    private String surname;

    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 3, max = 128)
    private String name;

    @Column(name = "patronymic")
    @Size(max = 128)
    private String patronymic;

    @Column(name = "phone", nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 5, max = 32)
    private String phone;
}
