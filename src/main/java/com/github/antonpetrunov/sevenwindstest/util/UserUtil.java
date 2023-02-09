package com.github.antonpetrunov.sevenwindstest.util;

import com.github.antonpetrunov.sevenwindstest.model.User;
import com.github.antonpetrunov.sevenwindstest.dto.UserTo;

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
