package com.github.anton_petrunov.seven_winds_test.web;

import com.github.anton_petrunov.seven_winds_test.model.User;
import com.github.anton_petrunov.seven_winds_test.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.github.anton_petrunov.seven_winds_test.util.ValidationUtil.checkNew;
import static com.github.anton_petrunov.seven_winds_test.util.ValidationUtil.checkNotFoundWithId;

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
        return checkNotFoundWithId(userRepository.findById(id).orElseThrow(), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("create new {}", user);
        checkNew(user);
        user = userRepository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(user);
    }
}
