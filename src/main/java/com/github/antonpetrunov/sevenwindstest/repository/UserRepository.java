package com.github.antonpetrunov.sevenwindstest.repository;

import com.github.antonpetrunov.sevenwindstest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
