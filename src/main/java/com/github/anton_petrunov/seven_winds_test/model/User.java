package com.github.anton_petrunov.seven_winds_test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @Column(name = "email", nullable = false)
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 128)
    private String email;

    @Column(name = "surname", nullable = false)
    @Size(max = 128)
    @NotNull
    @NotBlank
    @NotEmpty
    private String surname;

    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 128)
    private String name;

    @Column(name = "patronymic")
    @Size(max = 128)
    private String patronymic;

    @Column(name = "phone", nullable = false)
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 32)
    private String phone;

    public boolean isNew() {
        return id == null;
    }
}
