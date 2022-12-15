package com.github.anton_petrunov.seven_winds_test.util;

import com.github.anton_petrunov.seven_winds_test.error.IllegalRequestDataException;
import com.github.anton_petrunov.seven_winds_test.error.NotFoundException;
import com.github.anton_petrunov.seven_winds_test.model.User;

public class ValidationUtil {

    public static void checkNew(User user) {
        if (!user.isNew()) {
            throw new IllegalRequestDataException(user.getClass().getSimpleName() + " must be new");
        }
    }

    public static User checkNotFoundWithId(User user, int id) {
        checkNotFoundWithId(user != null, id);
        return user;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }
}
