package com.github.antonpetrunov.sevenwindstest.controller;

import com.github.antonpetrunov.sevenwindstest.dto.UserTo;
import com.github.antonpetrunov.sevenwindstest.model.User;
import com.github.antonpetrunov.sevenwindstest.service.EntityNotFoundException;
import com.github.antonpetrunov.sevenwindstest.service.UserService;
import com.github.antonpetrunov.sevenwindstest.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO: добавить пагинацию для метода getAll(), будем возвращать данные постранично
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserRestController {
    private UserService userService;

    @GetMapping
    public List<UserTo> findAll() {
        log.info("getAll users");
        return UserUtil.createTos(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public UserTo find(@PathVariable Integer id) throws EntityNotFoundException {
        log.info("get user {}", id);
        return UserUtil.createTo(userService.find(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserTo create(@Valid @RequestBody User user) {
        log.info("create new {}", user);
        return UserUtil.createTo(userService.create(user));
    }
}
