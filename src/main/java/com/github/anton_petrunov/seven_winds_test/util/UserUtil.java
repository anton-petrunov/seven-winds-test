package com.github.anton_petrunov.seven_winds_test.util;

import com.github.anton_petrunov.seven_winds_test.model.User;
import com.github.anton_petrunov.seven_winds_test.to.UserTo;

import java.util.List;

public class UserUtil {
    public static UserTo createTo(User user) {
        return new UserTo(
                user.getId(),
                user.getEmail(),
                user.getSurname(),
                user.getName(),
                user.getPatronymic(),
                user.getPhone()
        );
    }

    public static List<UserTo> createTos(List<User> users) {
        return users.stream()
                .map(UserUtil::createTo)
                .toList();
    }
}
