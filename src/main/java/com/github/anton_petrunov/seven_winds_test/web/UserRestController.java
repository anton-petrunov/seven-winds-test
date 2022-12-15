package com.github.anton_petrunov.seven_winds_test.web;

import com.github.anton_petrunov.seven_winds_test.model.User;
import com.github.anton_petrunov.seven_winds_test.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserRestController {
    static final String REST_URL = "/users";

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        log.info("getAll users");
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
        log.info("get user {}", id);
        return userRepository.findById(id).orElseThrow();
    }
}
