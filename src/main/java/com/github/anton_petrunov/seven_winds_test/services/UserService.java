package com.github.anton_petrunov.seven_winds_test.services;

import com.github.anton_petrunov.seven_winds_test.error.EntityNotFoundException;
import com.github.anton_petrunov.seven_winds_test.error.EntityNotNewException;
import com.github.anton_petrunov.seven_winds_test.model.User;
import com.github.anton_petrunov.seven_winds_test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
    }

    public User create(User user) {
        if (!user.isNew()) {
            throw new EntityNotNewException(user.getClass(), user.getId());
        }
        return userRepository.save(user);
    }
}
