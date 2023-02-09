package com.github.antonpetrunov.sevenwindstest.service;

import com.github.antonpetrunov.sevenwindstest.model.User;
import com.github.antonpetrunov.sevenwindstest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User find(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
    }

    public User create(User user) {
        if (!user.isNew()) {
            throw new EntityNotNewException(user.getClass(), user.getId());
        }
        return userRepository.save(user);
    }
}
