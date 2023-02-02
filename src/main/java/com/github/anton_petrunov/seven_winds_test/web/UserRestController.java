package com.github.anton_petrunov.seven_winds_test.web;

import com.github.anton_petrunov.seven_winds_test.exceptions.EntityNotFoundException;
import com.github.anton_petrunov.seven_winds_test.model.User;
import com.github.anton_petrunov.seven_winds_test.services.UserService;
import com.github.anton_petrunov.seven_winds_test.to.UserTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.github.anton_petrunov.seven_winds_test.util.UserUtil.createTo;
import static com.github.anton_petrunov.seven_winds_test.util.UserUtil.createTos;

//TODO: добавить пагинацию для метода getAll(), будем возвращать данные постранично
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserRestController {
    private UserService userService;

    @GetMapping
    public List<UserTo> getAll() {
        log.info("getAll users");
        return createTos(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public UserTo get(@PathVariable Integer id) throws EntityNotFoundException {
        log.info("get user {}", id);
        return createTo(userService.get(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserTo create(@Valid @RequestBody User user) {
        log.info("create new {}", user);
        return createTo(userService.create(user));
    }
}
