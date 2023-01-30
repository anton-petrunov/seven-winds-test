package com.github.anton_petrunov.seven_winds_test.web;

import com.github.anton_petrunov.seven_winds_test.model.User;
import com.github.anton_petrunov.seven_winds_test.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.github.anton_petrunov.seven_winds_test.util.ValidationUtil.checkNew;
import static com.github.anton_petrunov.seven_winds_test.util.ValidationUtil.checkNotFoundWithId;

//TODO: добавить пагинацию для метода getAll(), будем возвращать данные постранично
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserRestController {
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        log.info("getAll users");
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
        log.info("get user {}", id);
        return checkNotFoundWithId(userService.get(id), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        log.info("create new {}", user);
        checkNew(user);
        user = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users" + "/{id}")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(user);
    }
}
