package com.github.anton_petrunov.seven_winds_test.repository;

import com.github.anton_petrunov.seven_winds_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
