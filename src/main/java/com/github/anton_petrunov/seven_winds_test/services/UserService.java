package com.github.anton_petrunov.seven_winds_test.services;

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
        return userRepository.findById(id).orElseThrow();
    }

    public User create(User user) {
        return userRepository.save(user);
    }
}
