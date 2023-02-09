package com.github.antonpetrunov.sevenwindstest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@ToString
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

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
}
